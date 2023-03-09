<?php

namespace App\Form;

use App\Entity\Besoin;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;

class BesoinType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
         
            
            ->add('name')
            ->add('msg')
        
            ->add('association',ChoiceType::class, [
                'choices' => [
                    'scout' => 'scout',
                    'croissant rouge' => 'croissant rouge',
                    'unicef' => 'unicef',
                    'tunivision' => 'tunivision',
                    'libertad' => 'libertad',
                ]
            ])
            ->add('lieu')
            ->add('image',FileType::class, [
                'mapped' => false,
                'required' => false,
                
            ])
            ->add('type')

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Besoin::class,
        ]);
    }
}
