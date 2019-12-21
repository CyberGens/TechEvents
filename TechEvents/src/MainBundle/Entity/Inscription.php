<?php

namespace MainBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Inscription
 *
 * @ORM\Table(name="inscription")
 * @ORM\Entity
 */
class Inscription
{
    /**
     * @var string
     *
     * @ORM\Column(name="Event_name", type="string")
     */
    private $eventName;

    /**
     * @var string
     *
     * @ORM\Column(name="User_name", type="string")
     */
    private $userName;

    /**
     * @var integer
     *
     * @ORM\Column(name="Id_inscription", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idInscription;

    /**
     * @ORM\ManyToOne(targetEntity="Events")
     * @ORM\JoinColumn(name="idEvent",referencedColumnName="Id_event")
     */

    private  $event;

    /**
     * @return string
     */
    public function getEventName()
    {
        return $this->eventName;
    }

    /**
     * @param string $eventName
     */
    public function setEventName($eventName)
    {
        $this->eventName = $eventName;
    }

    /**
     * @return string
     */
    public function getUserName()
    {
        return $this->userName;
    }

    /**
     * @param string $userName
     */
    public function setUserName($userName)
    {
        $this->userName = $userName;
    }

    /**
     * @return int
     */
    public function getIdInscription()
    {
        return $this->idInscription;
    }

    /**
     * @param int $idInscription
     */
    public function setIdInscription($idInscription)
    {
        $this->idInscription = $idInscription;
    }

    /**
     * @return mixed
     */
    public function getEvent()
    {
        return $this->event;
    }

    /**
     * @param mixed $event
     */
    public function setEvent($event)
    {
        $this->event = $event;
    }


}

