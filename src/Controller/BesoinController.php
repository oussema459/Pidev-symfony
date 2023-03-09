<?php

namespace App\Controller;

use App\Entity\Besoin;
use App\Entity\TypeBesoin;
use App\Entity\Type;
use App\Form\BesoinType;
use App\Form\TypebesoinType;
use App\Repository\BesoinRepository;
use App\Repository\DonRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\String\Slugger\SluggerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use MercurySeries\Flashy\Flashy;
use Knp\Component\Pager\PaginatorInterface;
use Dompdf\Dompdf;
use Dompdf\Options;


#[Route('/besoin')]
class BesoinController extends AbstractController
{
    #[Route('/', name: 'app_besoin_index', methods: ['GET'])]
    public function index(BesoinRepository $besoinRepository): Response
    {
       
      
        return $this->render('besoin/index.html.twig', [
            'besoins' => $besoinRepository->findAll(),
            
        ]);
    }
   
    #[Route('/front', name: 'appfront_besoin_index', methods: ['GET'])]
    public function indexx(Request $request, BesoinRepository $besoinRepository, PaginatorInterface $paginator): Response
{
    
    $allBesoinsQuery = $besoinRepository->createQueryBuilder('p')
        ->orderBy('p.name', 'ASC')
        ->getQuery();

    // Paginate the results of the query
    $pagination = $paginator->paginate(
        // Doctrine Query, not results
        $allBesoinsQuery,
        // Define the page parameter
        $request->query->getInt('page', 1),
        // Items per page
        3
    );

    return $this->render('besoin/indexFront.html.twig', [
        'besoins' => $pagination,
    ]);
}




    #[Route('/new', name: 'app_besoin_new', methods: ['GET', 'POST'])]
    public function new(Request $request, BesoinRepository $besoinRepository, SluggerInterface $slugger = null): Response
    {
        $besoin = new Besoin();
        $form = $this->createForm(BesoinType::class, $besoin);
        $form->handleRequest($request);

    
        if ($form->isSubmitted() && $form->isValid()) {
            $photoC = $form->get('image')->getData();
            if ($photoC) {
                $originalImgName = pathinfo($photoC->getClientOriginalName(), PATHINFO_FILENAME);
                $newImgename = $originalImgName . '-' . uniqid() . '.' . $photoC->guessExtension();
    
                if ($slugger) {
                    $safeImgname = $slugger->slug($originalImgName);
                    $newImgename = $safeImgname . '-' . uniqid() . '.' . $photoC->guessExtension();
                }
    
                try {
                    $photoC->move(
                        $this->getParameter('imgb_directory'),
                        $newImgename
                    );
                } catch (FileException $e) {
                    // handle exception if something happens during file upload
                }
    
                $besoin->setImage($newImgename);
            }
    
            $besoinRepository->save($besoin, true);
    
            return $this->redirectToRoute('app_besoin_index', [], Response::HTTP_SEE_OTHER);
        }
    
        return $this->renderForm('besoin/new.html.twig', [
            'besoin' => $besoin,
            'form' => $form,
        ]);
    }
    
    

    #[Route('/{id}', name: 'app_besoin_show', methods: ['GET'])]
    public function show(Besoin $besoin): Response
    {
        return $this->render('besoin/show.html.twig', [
            'besoin' => $besoin,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_besoin_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Besoin $besoin, BesoinRepository $besoinRepository): Response
    {
        $form = $this->createForm(BesoinType::class, $besoin);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $besoinRepository->save($besoin, true);

            return $this->redirectToRoute('app_besoin_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('besoin/edit.html.twig', [
            'besoin' => $besoin,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_besoin_delete', methods: ['POST'])]
    public function delete(Request $request, Besoin $besoin, BesoinRepository $besoinRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$besoin->getId(), $request->request->get('_token'))) {
            $besoinRepository->remove($besoin, true);
        }

        return $this->redirectToRoute('app_besoin_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/front/prod/{id}', name: 'categorie_show_p', methods: ['GET'])]
    public function produitsParCategorie($id,Request $request, EntityManagerInterface $entityManager)
    {
        // Récupération de l'entité Catégorie
        $type = $this->getDoctrine()->getRepository(Type::class)->find($id);

        // Vérification si la catégorie existe
        if (!$type) {
            throw $this->createNotFoundException('La catégorie n\'existe pas');
        }

        // Récupération de tous les produits associés à la catégorie
        $besoins = $type->getBesoin();


        // Ou retourner une vue avec les produits
        return $this->render('besoin/indexFront.html.twig', [
            'besoins' => $besoins,
        ]);
    }
    #[Route('/rechercher/search', name: 'recherche_app_name')]
    public function search(Request $request): Response
    {
    
         $association = $request->query->get('association');   

        $besoins = $this->getDoctrine()
            ->getRepository(Besoin::class)
                ->findBy([
                  
                    'association' => $association,
                ]);

            return $this->render('besoin/indexFront.html.twig', [
                'besoins' => $besoins,   
            ]);
    }

    #[Route('/trie', name: 'trie_app_name')]
    public function trie(BesoinRepository $repository){
        $besoin=$repository->findBy(array(),array('name'=>'asc'));
        return $this->render('besoin/indexFront.html.twig',['besoin'=>$besoin]);

    }

    
    #[Route('/statique/stat', name: 'statique_app_name')]
    public function countAll(BesoinRepository $besoinRepo, DonRepository $donRepo): Response
    {
        $besoinCount = $besoinRepo->countBesoin();
        $donCount = $donRepo->countDon();

        return $this->render('besoin/statique.html.twig', [
            'besoinCount' => $besoinCount,
            'donCount' => $donCount
        ]);
    }
    









    #[Route('/pdf/stat', name: 'pdf_app_name')]
    public function indexpdf(Request $request, BesoinRepository $repository)
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
    
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
    
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('besoin/pdf.html.twig', [
            'besoins' => $repository->findAll()
        ]);
    
        // Load HTML to Dompdf
        $dompdf->loadHtml($html);
    
        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');
    
        // Render the HTML as PDF
        $dompdf->render();

        
        // Output the generated PDF to Browser (force download)
        $response = new Response();
        $response->headers->set('Content-Type', 'application/pdf');
        $response->headers->set('Content-Disposition', 'besoins;filename="mypdf.pdf"');
        $response->setContent($dompdf->output());
        return $response;
    }
    
}
