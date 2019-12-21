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



class BlogController extends Controller
{



    public function createAction(Request $request)
    {
        $form = $this->createForm(BlogPostType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            /**
             * @var $blogPost BlogPost
             */
            $blogPost = $form->getData();

            $em = $this->getDoctrine()->getManager();
            $em->persist($blogPost);
            $em->flush();
            $this->addFlash('message','Post Saved frer :D ');

            $manager = $this->get('mgilet.notification');
            $notif = $manager->createNotification('un blog'.''.$blogPost->getTitle().''.'est ajouté');
            $notif->setMessage('Ajout blog ');
            $notif->setLink('http://symfony.com/');
            $manager->addNotification(array($this->getUser()), $notif, true);
            return $this->redirectToRoute('list_blog');

        }

        return $this->render('@Client/blog/create.html.twig', [
            'form' => $form->createView()
        ]);
    }







    public function listAction(request $request  )
    {
        $em = $this->getDoctrine()->getManager();

        $blogPosts = $em->getRepository('ClientBundle:BlogPost')->findAll();

        $paginator  = $this->get('knp_paginator');

        $result = $paginator->paginate(
            $blogPosts,
            $request->query->getInt('page', 1)/*page number*/,
            $request->query->getInt('limit', 3)/*limit per page*/
        );


        return $this->render('ClientBundle:blog:list.html.twig', [
            'blog_posts' => $result,
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
        return $this->redirectToRoute("list_blog");
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
            return $this->redirectToRoute('list_blog', [
                'blogPost' => $blogPost,
            ]);

        }

        return $this->render('ClientBundle:blog:edit.html.twig', [
            'form' => $form->createView()
        ]);
    }








}