<?php

namespace MainBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * ParticipantsList
 *
 * @ORM\Table(name="participants_list")
 * @ORM\Entity
 */
class ParticipantsList
{
    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="Event_name", type="string")
     */
    private $eventName;

    /**
     * @var integer
     *
     * @ORM\Column(name="Num_plan", type="integer", nullable=false)
     */
    private $numPlan;
/**

 @var integer
     *
     * @ORM\Column(name="Id_list", type="integer", nullable=false)
     */
    private $idList;



    /**
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate($date)
    {
        $this->date = $date;
    }

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
     * @return int
     */
    public function getNumPlan()
    {
        return $this->numPlan;
    }

    /**
     * @param int $numPlan
     */
    public function setNumPlan($numPlan)
    {
        $this->numPlan = $numPlan;
    }


}

