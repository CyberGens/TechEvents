<?php

namespace MainBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * PlannedEvent
 *
 * @ORM\Table(name="planned_event")
 * @ORM\Entity
 */
class PlannedEvent
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
     * @ORM\Column(name="Num_plan", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $numPlan;

    /**
     * @var integer
     *
     * @ORM\Column(name="Level_interest", type="integer", nullable=false)
     */
    private $levelInterest;

    /**
     * @var string
     *
     * @ORM\Column(name="Side_note", type="string")
     */
    private $sideNote;


    /**

     *
     * @ORM\ManyToOne(targetEntity="Events")

     *   @ORM\JoinColumn(name="idEvent", referencedColumnName="Id_event")

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

    /**
     * @return int
     */
    public function getLevelInterest()
    {
        return $this->levelInterest;
    }

    /**
     * @param int $levelInterest
     */
    public function setLevelInterest($levelInterest)
    {
        $this->levelInterest = $levelInterest;
    }

    /**
     * @return string
     */
    public function getSideNote()
    {
        return $this->sideNote;
    }

    /**
     * @param string $sideNote
     */
    public function setSideNote($sideNote)
    {
        $this->sideNote = $sideNote;
    }


}

