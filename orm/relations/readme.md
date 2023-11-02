# Personel - Department Arası İlişki  ( One - to - Many)

One-to-Many bidirectional ilişkilerde
Üst Varlık: Çoğul olandır. (Personel)
    @ManyToOne olarak işaretlenir.

Alt Varlık: Tekil olandır. (Department)
    @OneToMany(mappedBy = "department")
    olarak işaretleyeceğiz.

1 departmanda birden çok personel olabilir. 
1 personel 1 departmanda çalışır.

departman - personel arası ilişki one-to-many
bu ilişki türünde many olan tarafa(personel) yeni bir
sütun ekleriz. Ve bu sütunda one tarafındaki(department)
PK'yı(id) FK(departman_id) olarak tutarız.




personel - departman arası ilişki many-to-one

## Personel - Araba Arası İlişki  ( One - to - One)
1 personel 1 araba sahibi olabilir.
1 araba 1 personele ait olabilir.

personel - car arası ilişki one-to-one
Personel tablosunda car_id FK olarak tutacak
bir yeni sütun açılır.




# Personel - Adres Arası İlişki  ( Many - to - Many)
1 personel birden çok adrese sahip olabilir.
1 adreste birden çok personel kalabilir.

personel - adres arası ilişki many-to-many

bu ilişki türünde yeni bir tablo oluşturup
burada her 2 tablonun PK'sını ayrı kolonlarda
tutarız.



