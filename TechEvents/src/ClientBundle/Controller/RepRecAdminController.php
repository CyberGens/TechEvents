<?php


namespace ClientBundle\Controller;



use ClientBundle\Entity\Reprec;
use Symfony\Component\HttpFoundation\Request;
use ClientBundle\Form\ReprecType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use ClientBundle\Form\ReclamationType;
use ClientBundle\Entity\Reclamation;


use Symfony\Component\Form\FormTypeInterface;


class RepRecAdminController extends Controller
{
    Public function AjoutRepRecAction(Request $request){
        $reprec = new RepRec();
        $rec=new Reclamation();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();

       // $Form=$this->createForm(ReclamationType::class,$rec);
        //$Form=$this->createForm(ReprecType::class,$reprec);
        //$Form->handleRequest($request);
        $test = "add";
        $form = $this->createForm(ReprecType::class, $reprec);
        $form = $form->handleRequest($request);

        if ($form->isValid()) {

            $reprec->setIdrec($_GET['id_rec']+0);
            $xd=$_GET['iduser']+0;

            $em = $this->getDoctrine()->getManager();
            $week = date("Y-m-d", strtotime("-1 week"));
            $query = $em->createQuery(
                'SELECT p FROM ClientBundle:Reclamation p  WHERE p.date >=:d1 ');
            $query->setParameter("d1", $week);

            $user = $this->container->get('security.token_storage')->getToken()->getUser();
        //    $idq = $user;


            $Rec = $em->getRepository('ClientBundle:Reclamation')->find($reprec->getIdrec());
            $reprec->setIdqui($xd);
            $Rec->setEtat(0);


            $em->persist($reprec);
            $em->persist($Rec);

            $em->flush();

            return $this->redirectToRoute('reclamations');

        }
        //1.3 Envoi du formulaire
        return $this->render('@Client/Reclamation/RepRec.html.twig',
            array('form' => $form->createView(), 'test' => $test));
   }



    public function afficherrAction()
    {
        //La création de l'Entity Manager par l'appelle de doctrine(notre ORM)
        $em=$this->getDoctrine();
        //La récupération des données avec Repository
        $tab=$em->getRepository(RepRec::class)->findAll();
        return $this->render('@Client/Reclamation/lista.html.twig',array('produits'=>$tab));
    }
}
