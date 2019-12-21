<?php

namespace UserBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * BlackList
 *
 * @ORM\Table(name="black_list", indexes={@ORM\Index(name="FK_User_File", columns={"Id_user"})})
 * @ORM\Entity
 */
class BlackList
{
    /**
     * @var integer
     *
     * @ORM\Column(name="Id_entry", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    protected $idEntry;



    /**
     * @var \User
     *
     * @ORM\OneToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="Id_user", referencedColumnName="id")
     * })
     */
    protected $idUser;


    /**
     * Get idEntry
     *
     * @return integer
     */

    /**
     * @var string
     *
     * @ORM\Column(name="Description", type="string", length=255, nullable=false)
     */
    protected $description;


    public function getIdEntry()
    {
        return $this->idEntry;
    }

    /**
     * Set description
     *
     * @param string $description
     *
     * @return BlackList
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set idUser
     *
     * @param \UserBundle\Entity\User $idUser
     *
     * @return BlackList
     */
    public function setIdUser(\UserBundle\Entity\User $idUser = null)
    {
        $this->idUser = $idUser;

        return $this;
    }

    /**
     * Get idUser
     *
     * @return \UserBundle\Entity\User
     */
    public function getIdUser()
    {
        return $this->idUser;
    }
}
