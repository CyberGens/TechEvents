<?php
/**
 * Created by PhpStorm.
 * User: Ahmed Hammouda
 * Date: 07/02/2018
 * Time: 20:13
 */

namespace ClientBundle\Controller;
use ClientBundle\Entity\BlogPost;
use ClientBundle\Entity\Promouvoir;
use ClientBundle\Form\PromouvoirType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller ;
use ClientBundle\Entity\Produit;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use ClientBundle\Entity\User;
use ClientBundle\Entity\Event;
use ClientBundle\Form\EventType;
use ClientBundle\Entity\Jaim;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;


use ClientBundle\Form\BlogPostType;



class BlogAdminController extends Controller
{








    public function listAction(request $request  )
    {
        $em = $this->getDoctrine()->getManager();

        $blogPosts = $em->getRepository('ClientBundle:BlogPost')->findAll();
        $notif=$this->get('mgilet.notification')->getAllUnseen();
        if($request->isXmlHttpRequest()){
            foreach ($notif as $n)
            {
                $nn=$this->get('mgilet.notification')->setAsSeen($n);
                $nn->setSeen(1);
                $this->getDoctrine()->getManager()->persist($nn);
                $this->getDoctrine()->getManager()->flush();
            }
            $notif=$this->get('mgilet.notification')->getAllUnseen();
            $count=count($notif);
            return new JsonResponse($count);
        }

        $paginator  = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $blogPosts,
            $request->query->getInt('page', 1)/*page number*/,
            $request->query->getInt('limit', 5)/*limit per page*/
        );

        $count=count($notif);

        return $this->render('ClientBundle:blog:list.html.twig', [
            'blog_posts' => $result,'count'=>$count,'notif'=>$notif
        ]);
    }





    /**
     * @param Request  $request
     * @param BlogPost $blogPost
     * @return \Symfony\Component\HttpFoundation\RedirectResponse
     *
     * @Route("/delete/{blogPost}", name="delete")
     */
    public function deleteAction(Request $request)

    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $Produit = $em->getRepository('ClientBundle:BlogPost')->find($id);
        $em->remove($Produit);
        $em->flush();
        return $this->redirectToRoute("adminlist_blog");
    }


    /**
     * @param Request  $request
     * @param BlogPost $blogPost
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|\Symfony\Component\HttpFoundation\Response
     *
     * @Route("/show/{blogPost}", name="show")
     */
    public function showAction(Request $request)
    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $blogPost=new BlogPost();
        $blogPost = $em->getRepository('ClientBundle:BlogPost')->find($id);


        return $this->render('ClientBundle:blog:view.html.twig',['blogPost'=>$blogPost]);
    }



    public function editAction(Request $request)


    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $blogPost=new BlogPost();
        $blogPost = $em->getRepository('ClientBundle:BlogPost')->find($id);
        $form = $this->createForm(BlogPostType::class, $blogPost);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            $em = $this->getDoctrine()->getManager();
            $em->flush();

            // for now
            return $this->redirectToRoute('adminlist_blog', [
                'blogPost' => $blogPost,
            ]);

        }

        return $this->render('ClientBundle:blog:edit.html.twig', [
            'form' => $form->createView()
        ]);
    }








}
