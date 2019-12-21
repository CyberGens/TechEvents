<?php
/**
 * Created by PhpStorm.
 * User: Ahmed Hammouda
 * Date: 07/02/2018
 * Time: 20:13
 */

namespace ClientBundle\Controller;

use ClientBundle\Entity\Promouvoir;
use AdminBundle\Form\ProduitType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller ;
use ClientBundle\Entity\Produit;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use ClientBundle\Entity\Event;
use ClientBundle\Form\EventType;
use ClientBundle\Entity\Jaim;
use ClientBundle\Entity\Lignecommande;
use Symfony\Component\Validator\Constraints\DateTime;
use  ClientBundle\Entity\Commande;
use  ClientBundle\Entity\Paiement;
use ClientBundle\Entity\Reclamation ;
use ClientBundle\Form\ReclamationType;
use ClientBundle\Entity\User;


class ProduitController extends Controller
{

    public function ChichaAction()
    {


        $produit= new Produit();
        $em = $this->getDoctrine()->getManager();
        $produits = $em->getRepository("ClientBundle:Produit")->findAll();


        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.categorie =:h'
        );
        $query->setParameter('h',"Chicha");
        $produits = $query->getResult();


        return $this->render('ClientBundle:Produit:Chicha.html.twig',array('produits'=>$produits));


    }


    public function voirplusAction(Request $request)
    {
        $id=$request->get('id');

        $em= $this->getDoctrine()->getManager();
        $Produit=$em->getRepository('ClientBundle:Produit')->find($id);


        $reference=$Produit->getReference();
        $nom=$Produit->getNom();
        $categorie=$Produit->getCategorie();
        $prix=$Produit->getPrix();
        $description=$Produit->getDescription();
        $image=$Produit->getImage();
        $nbjaimes=$Produit->getNbjaimes();



        return $this->render('ClientBundle:Produit:voiplus.html.twig',array('ref'=>$reference,'nom'=>$nom,'cat'=>$categorie,'p'=>$prix,'des'=>$description,'img'=>$image,'nb'=>$nbjaimes));





    }


    /**
     * @return Response
     */
    public function TraditionalFoodAction()
    {


        $produit= new Produit();
        $em = $this->getDoctrine()->getManager();
        $produits = $em->getRepository("ClientBundle:Produit")->findAll();


        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.categorie =:h'
        );
        $query->setParameter('h',"TraditionalFood");
        $produits = $query->getResult();


        return $this->render('ClientBundle:Produit:TraditionalFood.html.twig',array('produits'=>$produits));


    }
    public function ArtisanaAction()
    {


        $produit= new Produit();
        $em = $this->getDoctrine()->getManager();
        $produits = $em->getRepository("ClientBundle:Produit")->findAll();


        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.categorie =:h'
        );
        $query->setParameter('h',"Artisana");
        $produits = $query->getResult();


        return $this->render('ClientBundle:Produit:Artisana.html.twig',array('produits'=>$produits));


    }
    public function OtherAction()
    {


        $produit= new Produit();
        $em = $this->getDoctrine()->getManager();
        $produits = $em->getRepository("ClientBundle:Produit")->findAll();


        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.categorie =:h'
        );
        $query->setParameter('h',"Other");
        $produits = $query->getResult();


        return $this->render('ClientBundle:Produit:Other.html.twig',array('produits'=>$produits));


    }



    public function VetementAction()
    {
        return $this->render('ClientBundle:Produit:Vetement.html.twig');
    }








    public function LikeProduitAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $week=date("Y-m-d", strtotime("-1 week"));

        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.date >=:d1 '
        );
        $query->setParameter('d1',$week);

        $produits = $query->getResult();

        $user=$this->getUser();
        $idu=$user->getId();

        $id=$request->get('id');

        $Produit=$em->getRepository('ClientBundle:Produit')->find($id);
        $jaim=$em->getRepository('ClientBundle:Jaim')->findOneBy(array('idClient'=>$idu,'idroduit'=>$id));


        if(empty($jaim)){
            $jaim=new Jaim();
            $jaim->setEtat(1);
            $Produit->setNbjaimes($Produit->getNbjaimes() + 1);
            $jaim-> setIdClient($user);
            $jaim-> setIdroduit($Produit);
            $em->persist($jaim);
            $em->persist($Produit);
            $em->flush();
            ?><script>alert('thanks for ur like <3 ');</script><?php

            return $this->render('ClientBundle:Default:hello.html.twig',array('produits'=>$produits));

        }



        ?><script>alert('u already like it :p ');</script><?php

        return $this->render('ClientBundle:Default:hello.html.twig',array('produits'=>$produits));
    }





    public function AjouterAuPanierAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $week=date("Y-m-d", strtotime("-1 week"));

        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.date >=:d1 '
        );
        $query->setParameter('d1',$week);

        $produits = $query->getResult();
        if ($request->isMethod("POST")) {

            $quantite = $request->get('quantite');
            $idProduit = $request->get('id');

            $user = $this->getUser();
            $id = $user->getId();
            $etat = 0;
            $user = $em->getRepository("ClientBundle:User")->find($id);

            $CMD1 = $em->getRepository("ClientBundle:Commande")->findOneBy(array("idclient" => $id, "etat" => $etat));

            if (empty($CMD1)) {
                $CMD1 = new Commande();
                $CMD1->setIdclient($user);
                $etat = 0;
                $CMD1->setEtat($etat);
                $CMD1->setDate(new \DateTime('now'));
                $em->persist($CMD1);
                $em->flush();
            }

            $produit = $em->getRepository("ClientBundle:Produit")->find($idProduit);
            if ($produit->getQuantite() > $quantite) {


                $ligneCMD = new Lignecommande();
                $ligneCMD->setIdproduit($produit);
                $ligneCMD->setIdcommande($CMD1);
                $ligneCMD->setPrix($produit->getPrix());
                $ligneCMD->setQuantite($quantite);
                $prix = $produit->getPrix();
                $total = $prix * $quantite;
                $ligneCMD->setTotal($total);
                $quantiteproduit = $produit->getQuantite();
                $qt = $quantiteproduit - $quantite;
                $produit->setQuantite($qt);


                //////////////////////////////////////////////////////////////////////


                $em->persist($produit);
                $em->persist($ligneCMD);
                $em->flush();
                ?><script>alert('Produit ajouter :)');</script><?php
                return $this->render('ClientBundle:Default:hello.html.twig', array(
                    'produits' => $produits));
            } else {
                ?><script>alert('Dsl La quantite n est plus disponible :( ');</script><?php
                return $this->render('ClientBundle:Default:hello.html.twig', array(
                    'produits' => $produits));
            }
        }


        return $this->render('ClientBundle:Default:hello.html.twig', array(
            'produits' => $produits));

    }



    public function homeAction()
    {


        $produit= new Produit();
        $em = $this->getDoctrine()->getManager();
        //$produits = $em->getRepository("ClientBundle:Produit")->findAll();
        //$enseignes = $em->getRepository("ClientBundle:Enseigne")->findAll();

        $datej=new \DateTime('now');

        $week=date("Y-m-d", strtotime("-1 week"));

        $query = $em->createQuery(
            'SELECT p
    FROM ClientBundle:Produit p
    WHERE p.date >=:d1 '
        );
        $query->setParameter('d1',$week);

        $produits = $query->getResult();
        return $this->render('ClientBundle:Default:hello.html.twig',array('produits'=>$produits));
    }


    public function OtherProduitAction(Request $request)
    {


        $em=$this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $produits=$em->getRepository("ClientBundle:Produit")->findBy(array("id_user"=>$idu));



        $datej=new \DateTime('now');
        $produit= new Produit();
        $Form=$this->createForm(ProduitType::class,$produit);
        $Form->handleRequest($request);
        if ($Form->isValid()){

            $produit->setDate($datej);
            $file = $Form['image']->getData();
            $produit->setImage($produit->getNom().".png");

            $file->move($this->getParameter('image_forfait'), $produit->getImage());

            $produit->setIdUser($this->getUser());
            $produit->setNbjaimes(0);

            $em=$this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();
            ?><script>alert('Produit ajouter :)');</script><?php
            return $this->redirectToRoute('OtherProduit');


        }
        return $this->render("ClientBundle:Produit:OtherProduit.html.twig",array("Form"=>$Form->createView(),'produits'=>$produits));

    }
    public function DeleteOtherProduitAction(Request $request)
    {
        $id=$request->get('id');
        $em= $this->getDoctrine()->getManager();
        $Produit=$em->getRepository('ClientBundle:Produit')->find($id);
        $em->remove($Produit);
        $em->flush();
        return $this->redirectToRoute("OtherProduit");
    }



    public function ModifierOtherProduitAction(Request $request)
    {


        $id=$request->get('id');
        $em= $this->getDoctrine()->getManager();
        $user=$this->getUser();
        $idu=$user->getId();
        $produits=$em->getRepository("ClientBundle:Produit")->findBy(array("id_user"=>$idu));
        $Produit=$em->getRepository('ClientBundle:Produit')->find($id);
        $form=$this->createForm(ProduitType::class,$Produit);
        $form->handleRequest($request);
        $img=$Produit->getImage();
        if ($form->isValid())
        {
            $file = $form['image']->getData();
            $Produit->setImage($Produit->getNom().".png");

            $file->move($this->getParameter('image_forfait'), $Produit->getImage());

            $em->persist($Produit);
            $em->flush();
            return $this->redirect( $this->generateUrl("OtherProduit"));

        }
        return $this->render('ClientBundle:Produit:ModifierOtherProduit.html.twig',array(
            "form"=>$form->createView(),"img"=>$img,'produits'=>$produits
        ));

    }



}