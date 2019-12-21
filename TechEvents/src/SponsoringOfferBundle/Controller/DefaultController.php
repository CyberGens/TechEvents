<?php

namespace SponsoringOfferBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('SponsoringOfferBundle:Default:index.html.twig');
    }
}
