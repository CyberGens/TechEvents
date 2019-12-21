<?php


namespace EshopBundle\Controller;
use EshopBundle\Entity\Product;

use EshopBundle\Form\ProductType;
use EshopBundle\Form\ProductType1;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extention\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\File;




class ProductController extends Controller
{
    Public function ajoutAction(Request $request)
    {
        $product = new Product();
        //$this->getUser()->getId();

        $form = $this->createForm(ProductType::class, $product);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $em = $this->getDoctrine()->getManager();
            //$product->uploadProfilePicture();

            $em->persist($product);

            $em->flush();
            $this->get('session')->getFlashBag()->add('notice','Produit ajouté avec succés');

            return $this->redirectToRoute("eshop_listpage");


        }
        return $this->render('@Eshop/Product/ajout.html.twig', array(
            'form' => $form->createView(),
        ));


    }

    Public function listAction()
    {

        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->findAll();



        Return $this->render('@Eshop/Product/list.html.twig', array(
            'mod' => $product));
    }


    Public function supprimerAction(Request $request, $id)
    {

        $product = new Product();
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->find($id);
        $em->remove($product);
        $em->flush();


        Return $this->redirectToRoute("eshop_listpage");


    }

    Public function modifierAction(Request $request, $id)
    {


        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->find($id);

        $form = $this->createForm(ProductType1::class, $product);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {

            $em->persist($product);
            $em->flush();

            Return $this->redirectToRoute("eshop_listpage");
        }

        Return $this->render('@Eshop/Product/modifier.html.twig',
            array(
                'form' => $form->createView(),
            ));


    }

    Public function RechercheAction(Request $request)
    {


        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->findAll();
        if ($request->isMethod('POST')) {
            $name = $request->get('name');//la recherche se fait par nom
           // $product = $em->getRepository('EshopBundle:Product')
            $product = $em->getRepository(Product::class)
                ->findBy(array(
                    "name" => $name
                ));
        }
        Return $this->render('@Eshop/Product/recherche.html.twig'
            , array(
                "mod" => $product
            ));
    }
    public function clickAction()
    {
        return $this->redirectToRoute("eshop_recherchepage");
    }







}




