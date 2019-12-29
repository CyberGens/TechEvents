<?php

namespace UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class WebServiceController extends Controller

{
    public function allUsersAction(){
        $users=$this->getDoctrine()->getManager()
            ->getRepository('UserBundle:User')
            ->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($users);
        return new JsonResponse($formatted);
    }
    public function loginAction(Request $request)
    {
        $pw=$request->query->get('password');
        $hash=$request->query->get('hash');
        if (password_verify($pw,$hash))
        return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'true'));
        return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'false'));
    }

}
