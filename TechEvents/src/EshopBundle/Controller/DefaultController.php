<?php

namespace EshopBundle\Controller;

use EshopBundle\Entity\Panier;
use EshopBundle\Entity\Product;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{

    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $product = $em->getRepository(Product::class)->findAll();

        return $this->render('@Eshop/Default/index.html.twig', array(
            'product' => $product));
    }

    public function productAction(Product $product)
    {
        $panier = new Panier();
        $em = $this->getDoctrine()->getManager();
        $panier->setIdUser(1);
        $panier->setIdProd($product);
        $panier->setQuantite(1);
        $panier->setPrix($product->getPrice());
        $panier->setNameProd($product->getName());
        $panier->setTotal($product->getPrice()*1);
        $product->setQuantity($product->getQuantity()-1);
        $paniers=$em->getRepository(Panier::class)->findAll();
        $em->persist($product);
        $em->flush();
        $em->persist($panier);
        $em->flush();
        $deleteForm = $this->createDeleteForm($product);
        return $this->render('@Eshop/panier/index.html.twig', array(
            'paniers' =>$paniers,
            'product' => $product,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    private function createDeleteForm(Product $product)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('panier_delete', array('id' => $product->getid())))
            ->setMethod('DELETE')
            ->getForm()
            ;
    }



}
