<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PointsSystem
 *
 * @ORM\Table(name="points_system")
 * @ORM\Entity
 */
class PointsSystem
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_inscription", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idInscription;

    /**
     * @var integer
     *
     * @ORM\Column(name="Id_user", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     */
    private $idUser;

    /**
     * @var integer
     *
     * @ORM\Column(name="points", type="integer", nullable=false)
     */
    private $points;


}

