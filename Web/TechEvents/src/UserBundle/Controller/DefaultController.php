<?php

namespace UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $sponsorFile=$this->getDoctrine()->getRepository()->findBy($user);
        return $this->render('@User/Default/index.html.twig',
            ['sponsorFile' => $sponsorFile]);
    }
}
