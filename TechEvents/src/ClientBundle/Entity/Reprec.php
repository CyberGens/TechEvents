<?php

namespace ClientBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Reprec
 *
 * @ORM\Table(name="reprec", uniqueConstraints={@ORM\UniqueConstraint(name="idrec", columns={"idrec"})}, indexes={@ORM\Index(name="idqui", columns={"idqui"})})
 * @ORM\Entity
 */
class Reprec
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="message", type="string", length=255, nullable=false)
     */
    private $message;

    /**
     * @var integer
     *
     * @ORM\Column(name="idrec", type="integer", nullable=false)
     */
    private $idrec;

    /**
     * @var integer
     *
     * @ORM\Column(name="idqui", type="integer", nullable=false)
     */
    private $idqui;



    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set message
     *
     * @param string $message
     *
     * @return Reprec
     */
    public function setMessage($message)
    {
        $this->message = $message;

        return $this;
    }

    /**
     * Get message
     *
     * @return string
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     * Set idrec
     *
     * @param integer $idrec
     *
     * @return Reprec
     */
    public function setIdrec($idrec)
    {
        $this->idrec = $idrec;

        return $this;
    }

    /**
     * Get idrec
     *
     * @return integer
     */
    public function getIdrec()
    {
        return $this->idrec;
    }

    /**
     * Set idqui
     *
     * @param integer $idqui
     *
     * @return Reprec
     */
    public function setIdqui($idqui)
    {
        $this->idqui = $idqui;

        return $this;
    }

    /**
     * Get idqui
     *
     * @return integer
     */
    public function getIdqui()
    {
        return $this->idqui;
    }
}
