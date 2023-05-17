package models

import javax.persistence.*

// CREATE TABLE card (
//   id int PRIMARY KEY,
//   name varchar(255),
//   description varchar(255),
//   position varchar(255)
// );

@Entity
@Table(name = "result")
class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "throwable")
    var throwable: String? = null

    @Column(name = "time")
    var time: String = ""

    @Column(name = "is_success")
    var isSuccess: Boolean = false
}