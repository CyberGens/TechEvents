<?php
// src/AppBundle/Entity/User.php

namespace UserBundle\Entity;

use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;

	/**
	 * @ORM\Entity
	 * @ORM\Table(name="fos_user")
    */
class User extends BaseUser
{
        /**
         * @ORM\Id
         * @ORM\Column(type="integer")
         * @ORM\GeneratedValue(strategy="AUTO")
         */
    protected $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Photo", type="text", length=65535, nullable=false)
     */
    protected $photo;

    /**
     * @var string
     *
     * @ORM\Column(name="Firstname", type="string", length=50, nullable=false)
     */
    protected $firstname;

    /**
     * @var string
     *
     * @ORM\Column(name="Lastname", type="string", length=50, nullable=false)
     */
    protected $lastname;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Birthdate", type="date", nullable=true)
     */
    protected $birthdate;


    /**
     * @var string
     *
     * @ORM\Column(name="Address", type="text", length=65535, nullable=false)
     */
    protected $address;

    /**
     * @var string
     *
     * @ORM\Column(name="Phone_number", type="string", length=50, nullable=false)
     */
    protected $phoneNumber;

    /**
     * @var string
     *
     * @ORM\Column(name="Role", type="string", length=20, nullable=false)
     */
    protected $role = 'User';


    /**
     * Constructor
     */


    public function __construct()
	    {
        	        parent::__construct();
	        // your own logic
	    }

    /**
     * Set photo
     *
     * @param string $photo
     *
     * @return User
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;

        return $this;
    }

    /**
     * Get photo
     *
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * Set firstname
     *
     * @param string $firstname
     *
     * @return User
     */
    public function setFirstname($firstname)
    {
        $this->firstname = $firstname;

        return $this;
    }

    /**
     * Get firstname
     *
     * @return string
     */
    public function getFirstname()
    {
        return $this->firstname;
    }

    /**
     * Set lastname
     *
     * @param string $lastname
     *
     * @return User
     */
    public function setLastname($lastname)
    {
        $this->lastname = $lastname;

        return $this;
    }

    /**
     * Get lastname
     *
     * @return string
     */
    public function getLastname()
    {
        return $this->lastname;
    }

    /**
     * Set birthdate
     *
     * @param \DateTime $birthdate
     *
     * @return User
     */
    public function setBirthdate($birthdate)
    {
        $this->birthdate = $birthdate;

        return $this;
    }

    /**
     * Get birthdate
     *
     * @return \DateTime
     */
    public function getBirthdate()
    {
        return $this->birthdate;
    }

    /**
     * Set address
     *
     * @param string $address
     *
     * @return User
     */
    public function setAddress($address)
    {
        $this->address = $address;

        return $this;
    }

    /**
     * Get address
     *
     * @return string
     */
    public function getAddress()
    {
        return $this->address;
    }

    /**
     * Set phoneNumber
     *
     * @param string $phoneNumber
     *
     * @return User
     */
    public function setPhoneNumber($phoneNumber)
    {
        $this->phoneNumber = $phoneNumber;

        return $this;
    }

    /**
     * Get phoneNumber
     *
     * @return string
     */
    public function getPhoneNumber()
    {
        return $this->phoneNumber;
    }

    /**
     * Get role
     *
     * @return string
     */
    public function getRole()
    {
        return $this->role;
    }

    /**
     * Set role
     *
     * @param string $role
     *
     * @return User
     */
    public function setRole($role)
    {
        $this->role = $role;

        return $this;
    }
}
