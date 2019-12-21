<?php

namespace UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;

class LoginController extends Controller
{
    /**
     *@Route("/admin/home")
     */
    public function redirectAction(){
        $authChecker=$this->container->get('security.authorization_checker');
        if($authChecker->isGranted('ROLE_ADMIN')) {
            return $this->render('@User/Default/admin_home.html.twig');
        }
        elseif($authChecker->isGranted('ROLE_USER')) {
            return $this->render('@User/Default/home.html.twig');
        }
        else{
            return $this->render('@User/Security/login.html.twig');
        }

    }
}
