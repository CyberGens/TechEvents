<?php

namespace LocataireBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class WebServiceController extends Controller
{
    public function allLocalAction(){
        $locals=$this->getDoctrine()->getManager()
            ->getRepository('LocataireBundle:Local')
            ->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($locals);
        return new JsonResponse($formatted);
    }
}
