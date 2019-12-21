<?php

namespace MainBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * News_feed
 *
 * @ORM\Table(name="news_feed")
 * @ORM\Entity
 */
class News_feed
{
    /**
     * @var string
     *
     * @ORM\Column(name="Event_name", type="string")
     */
    private $eventName;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_feed", type="date" , nullable=false))
     */
    private $dateFeed;

    /**
     * @var integer
     *
     * @ORM\Column(name="Id_feed", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idFeed;



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
     * @return \DateTime
     */
    public function getDateFeed()
    {
        return $this->dateFeed;
    }

    /**
     * @param \DateTime $dateFeed
     */
    public function setDateFeed($dateFeed)
    {
        $this->dateFeed = $dateFeed;
    }

    /**
     * @return int
     */
    public function getIdFeed()
    {
        return $this->idFeed;
    }

    /**
     * @param int $idFeed
     */
    public function setIdFeed($idFeed)
    {
        $this->idFeed = $idFeed;
    }


}

