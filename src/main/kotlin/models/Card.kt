package models

import javax.persistence.*

// CREATE TABLE card (
//   id int PRIMARY KEY,
//   name varchar(255),
//   description varchar(255),
//   position varchar(255)
// );

@Entity
@Table(name = "card")
class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "description")
    var description: String = ""

    @Column(name = "position")
    var position: String = ""
}