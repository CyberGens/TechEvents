<?php


namespace ClientBundle\Controller;
use ClientBundle\Entity\Reclamation;
use ClientBundle\Form\ReclamationType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Bundle\FrameworkBundle\Tests\Fixtures\Validation\Category;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use ClientBundle\Entity\Mail;
use ClientBundle\Entity\User;



class ReclamationController extends Controller

{
    public function ReclamationAction(Request $request)
    {

        $em=$this->getDoctrine()->getManager();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $idu = $user;
        $produits=$em->getRepository("ClientBundle:Reclamation")->findBy(array("iduser"=>$idu->getId()));

        $datej=new \DateTime('now');


        $produit= new Reclamation();

        $Form=$this->createForm(ReclamationType::class,$produit);
        $Form->handleRequest($request);
        $produit->setEtat(1);
        $datej=new \DateTime('now');

        if ($Form->isValid()) {
            $produit->setDate($datej);
            $produit->setIdUser($idu);


            $message = \Swift_Message::newInstance()->setSubject('Accusé de réception')
                ->setFrom('tunisiamalleverywhere@gmail.com')
                ->setTo('lina.sahli@esprit.tn')
                ->setBody($this->renderView('@Client/Reclamation/emailAmal.html.twig',array('nom'=>'lina'),'text/html'));
            $this->get('mailer')->send($message);


            $em=$this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();


            return $this->redirectToRoute('Reclamation_homepage');
        }
        return $this->render("@Client/Reclamation/Reclamation.html.twig",array("Form"=>$Form->createView(),'produits'=>$produits));
    }
    public function ModifierreclamationAction(Request $request)
    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();

        $idu = $user;

        $produits = $em->getRepository("ClientBundle:Reclamation")->findBy(array("iduser" => $idu->getId()));
        $Produit = $em->getRepository('ClientBundle:Reclamation')->find($id);
        $form = $this->createForm(ReclamationType::class, $Produit);
        $form->handleRequest($request);
        if ($form->isValid()) {
            $Produit->setIdUser($this->getUser());

            $em->persist($Produit);
            $em->flush();
            return $this->redirect($this->generateUrl("Reclamation_homepage"));

        }
        return $this->render('@Client/Reclamation/ModifierReclamation.html.twig', array(
            "form" => $form->createView(), 'produits' => $produits
        ));
    }



    Public function RechercheAction(Request $request)
    {

        $etat = $request->get('etat');//la recherche se fait par etat
        $em = $this->getDoctrine()->getManager();
        $recla = $em->getRepository(Reclamation::class)->findBy(array("etat" => $etat));
        if (isset($etat) ) {
            $em=$this->getDoctrine();
            // $recla = $em->getRepository('ClientBundle:Reclamation')
               // ->findBy(array("etat" => $etat));
            return $this->render('@Client/Reclamation/ListRechercheRec.html.twig',
                array('produits'=>$recla));

        }
        return $this->render('@Client/Reclamation/recherche.html.twig',array('produits'=>$recla));
    }
    public function afficheAction()
    {
        //La création de l'Entity Manager par l'appelle de doctrine(notre ORM)
        $em=$this->getDoctrine();
        //La récupération des données avec Repository
        $tab=$em->getRepository(Reclamation::class)->findAll();
        return $this->render('@Client/Reclamation/ListRechercheRec.html.twig',array('produits'=>$tab));
    }



    public function helloAction($clab)
    {
        //$classe='4InfoB1';
        return $this->render('@Client/Reclamation/hello.html.twig',array('zz'=>$clab));
    }
}
