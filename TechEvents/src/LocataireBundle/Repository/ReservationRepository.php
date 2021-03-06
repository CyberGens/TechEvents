<?php

namespace LocataireBundle\Repository;

use LocataireBundle\Entity\Reservation;
use LocataireBundle\Entity\Local;

/**
 * ReservationRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class ReservationRepository extends \Doctrine\ORM\EntityRepository
{

    /**
     * @param $reservation
     * @return array
     */
    public function findByRI($reservation)
    {
        $entityManager = $this->getEntityManager();

        $query = $entityManager->createQuery('SELECT r
    FROM LocataireBundle\Entity\Reservation r
    WHERE ((r.dateDebut BETWEEN :dd AND :df)OR(r.dateFin BETWEEN :dd AND :df))AND r.idLocal=:idloc
    '
        )->setParameter('dd', $reservation->getDateDebut())
            ->setParameter('df',$reservation->getDateFin())
            ->setParameter('idloc',$reservation->getIdLocal()->getIdLoc());

        return $query->getResult();
    }
}
