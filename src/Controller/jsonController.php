<?php

namespace App\Controller;

use App\Entity\Besoin;
use App\Repository\BesoinRepository;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;




class jsonController extends AbstractController
{
  

    #[Route('/allBesoin/json/ok', name: 'appjsonaffichage', methods: ['GET'])]
    public function getBesoinnnnnnn(BesoinRepository $ok , SerializerInterface $serializer): Response
    {
        $jdon = $ok->findAll();
    
        $json = $serializer->serialize($jdon,'json',['groups'=>"besoin"]);
        
        return new Response($json);
    }

    #[Route("/besoinJson/{id}", name: "BesoinJson")]
    public function besoinId($id, NormalizerInterface $normalizer, BesoinRepository $repo)
    {
        $besoin = $repo->find($id);
        $besoinNormalises = $normalizer->normalize($besoin, 'json', ['groups' => "Besoin"]);
        return new Response(json_encode($besoinNormalises));
    }


    #[Route("addBesoinJSON/new", name: "addBesoinJSON", methods: ['GET'])]
    public function addBesointJSON(Request $req,   NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $besoin = new besoin();
        $besoin->setName($req->get('name'));
        $besoin->setMsg($req->get('msg'));
        $besoin->setAssociation($req->get('association'));
        $besoin->setLieu($req->get('lieu'));
       // $student->setImage($req->get('image'));
       // type de type manytoone
        $em->persist($besoin);
        $em->flush();

        $jsonContent = $Normalizer->normalize($besoin, 'json', ['groups' => 'besoin']);
        return new Response(json_encode($jsonContent));
    }

    #[Route("updateBesoinJSON/{id}", name: "updateBesoinJSON")]
    public function updateStudentJSON(Request $req, $id, NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $besoin = $em->getRepository(Besoin::class)->find($id);
        $besoin->setName($req->get('name'));
        $besooin->setMsg($req->get('msg'));
        $besoin->setAssociation($req->get('association'));
        $besoin->setLieu($req->get('lieu'));
       // $student->setImage($req->get('image'));

        $em->flush();

        $jsonContent = $Normalizer->normalize($besoin, 'json', ['groups' => 'besoin']);
        return new Response("besoin updated successfully " . json_encode($jsonContent));
    }

    #[Route("deleteBesoinJSON/{id}", name: "deleteBesoinJSON")]
    public function deleteBesoinJSON(Request $req, $id, NormalizerInterface $Normalizer)
    {

        $em = $this->getDoctrine()->getManager();
        $student = $em->getRepository(Besoin::class)->find($id);
        $em->remove($besoin);
        $em->flush();
        $jsonContent = $Normalizer->normalize($student, 'json', ['groups' => 'besoin']);
        return new Response("besoin deleted successfully " . json_encode($jsonContent));
    }
}