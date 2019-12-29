<?php

namespace LocataireBundle\Controller;

use Esprit\ApiBundle\Entity\Task;
use LocataireBundle\Entity\Local;
use LocataireBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class WebServiceController extends Controller
{
    public function allLocalAction(){
        $locals=$this->getDoctrine()->getManager()
            ->getRepository('LocataireBundle:Local')
            ->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($locals);
        return new JsonResponse($formatted);
    }
    public function allReservationAction(){
        $reservations=$this->getDoctrine()->getManager()
            ->getRepository('LocataireBundle:Reservation')
            ->findAll();
        $serializer=new Serializer([new ObjectNormalizer()]);
        $formatted=$serializer->normalize($reservations);
        return new JsonResponse($formatted);
    }
    public function newLocalAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $local=new Local();
        $pw=$request->query->get('pw');
        $id=$request->query->get('idU');
        $user=$this->getDoctrine()->getManager()
            ->getRepository('UserBundle:User')
            ->findOneBy(array('id'=>$id));
        if (password_verify($pw,$user->getPassword()))
        {
            $local->setIdUser($user);
                $local->setName($request->query->get('name'));
                $local->setAddress($request->query->get('address'));
                $local->setPrice($request->query->get('price'));
                $local->setSurface($request->query->get('surface'));
                $local->setCapacity($request->query->get('capacity'));
                $local->setImg($request->query->get('img'));
                $local->setX($request->query->get('x'));
                $local->setY($request->query->get('y'));
                $em->persist($local);
                $em->flush();
                return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'true'));
            }
        return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'false'));

    }
    public function newReservationAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $reservation=new Reservation();
        $pw=$request->query->get('pw');
        $idu=$request->query->get('idU');
        $idl=$request->query->get('idL');
        $local=$this->getDoctrine()->getManager()
            ->getRepository('LocataireBundle:Local')
            ->findOneBy(array('idLoc'=>$idl));
        $user=$this->getDoctrine()->getManager()
            ->getRepository('UserBundle:User')
            ->findOneBy(array('id'=>$idu));
        if (password_verify($pw,$user->getPassword()))
        {
            $reservation->setIdUser($user);
            $reservation->setIdOwner($local->getIdUser());
            $reservation->setDateDebut(new \DateTime(date( "Y-m-d", strtotime( $request->query->get('dd')))));
            $reservation->setDateFin(new \DateTime(date( "Y-m-d", strtotime( $request->query->get('df')))));
            $reservation->setIdLocal($local);
            if($this->getDoctrine()->getManager()->getRepository('LocataireBundle:Reservation')->findByRI($reservation)==null)
            {
            $em->persist($reservation);
            $em->flush();
            return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'true'));
            }
            else return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'false'));
        }
        return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'false'));

    }
    public function modifyLocalAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $pw=$request->query->get('pw');
        $id=$request->query->get('idU');
        $user=$this->getDoctrine()->getManager()
            ->getRepository('UserBundle:User')
            ->findOneBy(array('id'=>$id));
        if (password_verify($pw,$user->getPassword()))
        {
            $idl=$request->query->get('idL');
            $local=$this->getDoctrine()->getManager()
                ->getRepository('LocataireBundle:Local')
                ->findOneBy(array('idLoc'=>$idl));
            $local->setIdUser($user);
            $local->setName($request->query->get('name'));
            $local->setAddress($request->query->get('address'));
            $local->setPrice($request->query->get('price'));
            $local->setSurface($request->query->get('surface'));
            $local->setCapacity($request->query->get('capacity'));
            $local->setImg($request->query->get('img'));
            $local->setX($request->query->get('x'));
            $local->setY($request->query->get('y'));
            $em->persist($local);
            $em->flush();
            return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'true'));
        }
        return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'false'));

    }
    public function uploadImageAction(Request $request)
    {
        $brochureFile = $request->files->get('file');
        if ($brochureFile) {
            $originalFilename = pathinfo($brochureFile->getClientOriginalName(), PATHINFO_FILENAME);
            $safeFilename = transliterator_transliterate('Any-Latin; Latin-ASCII; [^A-Za-z0-9_] remove; Lower()', $originalFilename);
            $newFilename = $safeFilename . '-' . uniqid() . '.' . $brochureFile->guessExtension();
            try {
                $brochureFile->move(
                    $this->getParameter('images_directory'),
                    $newFilename
                );
                //$local->setImg($newFilename);
                return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>$newFilename));
            } catch (FileException $e) {
            }

        }
        return $this->render('@Locataire/jsonresponse.html.twig', array('msg'=>'false'));
    }
}
