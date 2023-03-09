<?php

namespace App\Entity;

use App\Repository\DonRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


#[ORM\Entity(repositoryClass: DonRepository::class)]
class Don
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
      /**
     * @Assert\Regex("/^[a-zA-Z]+(\s[a-zA-Z]+)*\s[a-zA-Z]+(\s[a-zA-Z]+)*$/")
     */
    private ?string $donneur = null;

    #[ORM\Column]
  
     /**
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     * )
     */
    private ?int $tel = null;

    #[ORM\Column(length: 255)]
    
    private ?string $type_don = null;

    #[ORM\Column(length: 255)]
    private ?string $lieu = null;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getDonneur(): ?string
    {
        return $this->donneur;
    }

    public function setDonneur(string $donneur): self
    {
        $this->donneur = $donneur;

        return $this;
    }

    public function getTel(): ?int
    {
        return $this->tel;
    }

    public function setTel(int $tel): self
    {
        $this->tel = $tel;

        return $this;
    }

    public function getTypeDon(): ?string
    {
        return $this->type_don;
    }

    public function setTypeDon(string $type_don): self
    {
        $this->type_don = $type_don;

        return $this;
    }

    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    public function setLieu(string $lieu): self
    {
        $this->lieu = $lieu;

        return $this;
    }
}
