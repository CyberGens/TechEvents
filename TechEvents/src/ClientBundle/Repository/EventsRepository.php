<?php

namespace ClientBundle\Repository;

/**
 * EventsRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class EventsRepository extends \Doctrine\ORM\EntityRepository
{
//    public function TrierEventsClient($etat)
//    {
//        $query = $this->getEntityManager()
//            ->createQuery("
//        select s  from ClientBundle:Events s where s.etat like :etat ORDER BY s.date")->setParameter('etat','%'.$etat.'%');
//
//        return $query->getResult();
//
//    }

    public function TrierEvents()
{
    $query = $this->getEntityManager()
        ->createQuery("
        select s  from ClientBundle:Events s  ORDER BY s.date");

    return $query->getResult();

}

    public function findEventDQL($titre)
    {
        $Query=$this->getEntityManager()
            ->createQuery(" 
 select v from ClientBundle:Events v where v.titre LIKE :titre

  ")
            ->setParameter('titre','%'.$titre.'%');


        return $Query->getResult();

    }

//    public function findEventDQLUSER($iduser)
//    {
//        $Query=$this->getEntityManager()
//            ->createQuery(" select e from ClientBundle:Events e where e.user LIKE :iduser ")
//            ->setParameter('user','%'.$iduser.'%');
//
//
//        return $Query->getResult();
//
//    }
    public function TrierEventsClient($etat)
    {
        $query = $this->getEntityManager()
            ->createQuery("
        select s  from ClientBundle:Events s where s.etat like :etat AND s.date > CURRENT_DATE () ORDER BY s.date")->setParameter('etat','%'.$etat.'%');

        return $query->getResult();

    }
    public function TrierEventsDoneClient($etat)
    {
        $query = $this->getEntityManager()
            ->createQuery("
        select s  from ClientBundle:Events s where s.etat like :etat AND s.date < CURRENT_DATE () ORDER BY s.date")->setParameter('etat','%'.$etat.'%');

        return $query->getResult();

    }
    public function findevent($titre)
    {
        $query=$this->getEntityManager()->createQuery("select m from ClientBundle:Events m where m.titre like :titre")
            ->setParameter('titre','%'.$titre.'%');
        return $query->getResult();
    }
    public function findeventarch($titre)
    {
        $query=$this->getEntityManager()->createQuery("select m from ClientBundle:Events m where m.titre like :titre AND m.date < CURRENT_DATE ()")
            ->setParameter('titre','%'.$titre.'%');
        return $query->getResult();
    }
    public function ArchiveEventsClient()
    {
        $query = $this->getEntityManager()
            ->createQuery("
        select s  from ClientBundle:Events s where  s.date < CURRENT_DATE () ORDER BY s.date");

        return $query->getResult();

    }


}
