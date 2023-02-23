<?php

declare(strict_types=1);

namespace DoctrineMigrations;

use Doctrine\DBAL\Schema\Schema;
use Doctrine\Migrations\AbstractMigration;

/**
 * Auto-generated Migration: Please modify to your needs!
 */
final class Version20230222141952 extends AbstractMigration
{
    public function getDescription(): string
    {
        return '';
    }

    public function up(Schema $schema): void
    {
        // this up() migration is auto-generated, please modify it to your needs
        $this->addSql('CREATE TABLE reservation (id INT AUTO_INCREMENT NOT NULL, event_id_id INT NOT NULL, user_id_id INT NOT NULL, date_reservation DATE NOT NULL, reservation_nb_ticket BIGINT NOT NULL, INDEX IDX_42C849553E5F2F7B (event_id_id), INDEX IDX_42C849559D86650F (user_id_id), PRIMARY KEY(id)) DEFAULT CHARACTER SET utf8mb4 COLLATE `utf8mb4_unicode_ci` ENGINE = InnoDB');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849553E5F2F7B FOREIGN KEY (event_id_id) REFERENCES event (id)');
        $this->addSql('ALTER TABLE reservation ADD CONSTRAINT FK_42C849559D86650F FOREIGN KEY (user_id_id) REFERENCES user (id)');
        $this->addSql('ALTER TABLE association ADD CONSTRAINT FK_FD8521CC294CCED FOREIGN KEY (category_type_id) REFERENCES category_type (id)');
        $this->addSql('CREATE INDEX IDX_FD8521CC294CCED ON association (category_type_id)');
    }

    public function down(Schema $schema): void
    {
        // this down() migration is auto-generated, please modify it to your needs
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849553E5F2F7B');
        $this->addSql('ALTER TABLE reservation DROP FOREIGN KEY FK_42C849559D86650F');
        $this->addSql('DROP TABLE reservation');
        $this->addSql('ALTER TABLE association DROP FOREIGN KEY FK_FD8521CC294CCED');
        $this->addSql('DROP INDEX IDX_FD8521CC294CCED ON association');
    }
}
