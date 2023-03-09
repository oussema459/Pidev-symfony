<?php

namespace App\Controller;

use App\Entity\Don;
use App\Entity\Besoin;
use App\Form\DonType;
use App\Repository\DonRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\BesoinRepository;

#[Route('/don')]
class DonController extends AbstractController
{
    #[Route('/', name: 'app_don_index', methods: ['GET'])]
    public function index(DonRepository $donRepository): Response
    {
        return $this->render('don/index.html.twig', [
            'dons' => $donRepository->findAll(),
        ]);
    }
    #[Route('/back', name: 'back_besoin_index', methods: ['GET'])]
    public function indexxx(BesoinRepository $besoinRepository): Response
    {
        return $this->render('besoin/back.html.twig', [
            'besoins' => $besoinRepository->findAll(),
        ]);
    }

    #[Route('/new', name: 'app_don_new', methods: ['GET', 'POST'])]
    public function new(Request $request, DonRepository $donRepository): Response
    {
        $don = new Don();
        $form = $this->createForm(DonType::class, $don);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $donRepository->save($don, true);

            return $this->redirectToRoute('back_besoin_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('don/new.html.twig', [
            'don' => $don,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_don_show', methods: ['GET'])]
    public function show(Don $don): Response
    {
        return $this->render('don/show.html.twig', [
            'don' => $don,
        ]);
    }

    #[Route('/{id}/edit', name: 'app_don_edit', methods: ['GET', 'POST'])]
    public function edit(Request $request, Don $don, DonRepository $donRepository): Response
    {
        $form = $this->createForm(DonType::class, $don);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $donRepository->save($don, true);

            return $this->redirectToRoute('app_don_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->renderForm('don/edit.html.twig', [
            'don' => $don,
            'form' => $form,
        ]);
    }

    #[Route('/{id}', name: 'app_don_delete', methods: ['POST'])]
    public function delete(Request $request, Don $don, DonRepository $donRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$don->getId(), $request->request->get('_token'))) {
            $donRepository->remove($don, true);
        }

        return $this->redirectToRoute('app_don_index', [], Response::HTTP_SEE_OTHER);
    }

    #[Route('/lieu/ccc', name: 'app_don_delete')]
    public function exemple()
    {
        // Récupération des entités depuis la base de données
        $entite1 = $this->getDoctrine()->getRepository(Don::class)->find(1);
        $entite2 = $this->getDoctrine()->getRepository(Besoin::class)->find(2);

        // Vérification si les attributs "lieu" sont identiques
        if (!$entite1 || !$entite2) {
            throw $this->createNotFoundException('Les entités n\'ont pas été trouvées.');
        }
        foreach ($entite2 as $champ) {
            if ($entite1->getLieu() === $champ) {
                $msg = "Le lieu est présent dans l'entité2 !";
                break; // arrêter la boucle dès qu'un champ correspond à l'attribut
            } else {
                $msg = "Le lieu n'est pas présent dans l'entité2.";
            }
        // Affichage du message dans le Twig
        return $this->render('besoin/indexFront.html.twig', [
            'msg' => $msg,
        ]);
    }
    }
    
    
    
    
    
    #[Route('/dons/stat', name: 'app_don_stat')]
    public function displayDonStats(DonRepository $donRepository): Response
    {
        $stats = $donRepository->countPeopleByTypeDon();

        return $this->render('don/statdon.html.twig', [
            'stats' => $stats,
        ]);
    }

}


   
