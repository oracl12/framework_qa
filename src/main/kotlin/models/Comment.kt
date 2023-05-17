package models

import javax.persistence.*

// CREATE TABLE comment (
//     id int PRIMARY KEY,
//     text varchar(255)
// );

@Entity
@Table(name = "comment")
class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Int = 0

    @Column(name = "text")
    var text: String = ""
}