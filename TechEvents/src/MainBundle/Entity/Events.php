<?php

namespace MainBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Events
 *
 * @ORM\Table(name="events")
 * @ORM\Entity
 */
class Events
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Id_event", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEvent;



    /**
     * @var string
     *
     * @ORM\Column(name="Name", type="string", length=55, nullable=false)
     */
    private $name;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date", type="date", nullable=false)
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="Location", type="string", length=55, nullable=false)
     */
    private $location;

    /**
     * @var integer
     *
     * @ORM\Column(name="Max_number", type="integer", nullable=false)
     */
    private $maxNumber;

    /**
     * @var string
     *
     * @ORM\Column(name="Sponsors", type="text", length=65535, nullable=false)
     */
    private $sponsors;

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="Category", type="string", length=55, nullable=false)
     */
    private $category;

    /**
     * @var integer
     *
     * @ORM\Column(name="Fin_status", type="integer", nullable=false)
     */
    private $finStatus;






    /**

     *
     * @ORM\ManyToOne(targetEntity="News_feed")
     *   @ORM\JoinColumn(name="idFeed", referencedColumnName="Id_feed")
     */

    private  $feed;

    /**
     * @return int
     */
    public function getIdEvent()
    {
        return $this->idEvent;
    }

    /**
     * @param int $idEvent
     */
    public function setIdEvent($idEvent)
    {
        $this->idEvent = $idEvent;
    }




    /**
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * @param string $name
     */
    public function setName($name)
    {
        $this->name = $name;
    }

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
    public function getLocation()
    {
        return $this->location;
    }

    /**
     * @param string $location
     */
    public function setLocation($location)
    {
        $this->location = $location;
    }

    /**
     * @return int
     */
    public function getMaxNumber()
    {
        return $this->maxNumber;
    }

    /**
     * @param int $maxNumber
     */
    public function setMaxNumber($maxNumber)
    {
        $this->maxNumber = $maxNumber;
    }

    /**
     * @return string
     */
    public function getSponsors()
    {
        return $this->sponsors;
    }

    /**
     * @param string $sponsors
     */
    public function setSponsors($sponsors)
    {
        $this->sponsors = $sponsors;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return string
     */
    public function getCategory()
    {
        return $this->category;
    }

    /**
     * @param string $category
     */
    public function setCategory($category)
    {
        $this->category = $category;
    }

    /**
     * @return int
     */
    public function getFinStatus()
    {
        return $this->finStatus;
    }

    /**
     * @param int $finStatus
     */
    public function setFinStatus($finStatus)
    {
        $this->finStatus = $finStatus;
    }

    public function __toString()
    {
        RETURN'GHASSEN';   }


}

