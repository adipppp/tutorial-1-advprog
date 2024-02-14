# tutorial-advprog
Nama: Fernanda Nadhiftya Putra<br>
NPM: 2206081686

## Exercise 2
### Reflection
1. Pertama, ada masalah penggunaan `@Autowired` pada ProductController. `@Autowired` seharusnya diletakkan di atas constructor ProductController, bukan di atas field `service`. Saya memperbaikinya dengan menambahkan constructor baru, meletakkan `@Autowired` di atas constructor, kemudian mengassign field `service` pada constructor tersebut.<br>
<br>
Selain itu, ada juga issue yang melarang penulisan string yang sama berulang kali tanpa menggunakan constants. Oleh karena itu, saya mendeclare sebuah constant `PRODUCT_ATTR_NAME` pada ProductController untuk merepresentasikan string "product"

2. Menurut saya, workflow pada repository ini sudah masuk definisi Continuous Integration dan Continuous Deployment. Pada repository ini, Continuous Integration terletak pada workflow `ci.yml` dan `build.yml`. Kemudian, Continuous Deployment terletak pada deployment ke Koyeb. Saat ada pull request atau push, script yml tersebut akan menjalankan github actions, kemudian Koyeb akan mendeteksi adanya perubahan pada repository dan akan melakukan deployment secara otomatis.

### Reflection 2


## Exercise 1
### Reflection 1
Setelah mempelajari prinsip Clean Code, saya sebisa mungkin tidak menggunakan comments dalam menulis kode. Agar kode tetap mudah dipahami, saya memberikan nama yang deskriptif pada setiap variabel dan method yang saya tulis. Selain itu, saya menerapkan error handling pada kode saya. Saat sebuah method mengalami hal yang tidak diinginkan, saya segera melakukan throw `RuntimeException` dengan message yang menjelaskan mengapa error tersebut terjadi. Exception tersebut kemudian akan di-handle oleh method lain yang memanggil method tersebut.

Contohnya, saat `Product` yang dicari tidak dapat ditemukan di `ProductRepository`, sebuah `RuntimeException` akan di-throw pada method `findOne()` punya `ProductRepository` tersebut. Method `findOne()` punya `ProductService` yang memanggil method `findOne()` punya `ProductRepository` akan menghandle error tersebut dengan cara melakukan throw kembali. Selain itu, validasi input lain yang saya terapkan pada `ProductService` juga akan melakukan throw `RuntimeException` jika input yang diperoleh dari `ProductController` tidak valid. Exception yang di-throw dari method pada `ProductService` tersebut pada akhirnya akan di-handle oleh `ProductController`. `ProductController` akan menampilkan tampilan yang berbeda-beda sesuai dengan input yang diberikan. Jika input tidak valid, `ProductController` tentu akan mendapatkan exception dari `ProductService` sehingga `ProductController` akan memberi tahu user/client bahwa input yang dimasukkan invalid.

Dengan ini, saya menerapkan <i>separation of concerns</i> dalam menghandle sebuah exception.

### Reflection 2
1. Untuk branch `unit-test`, saya rasa <i>code coverage</i>nya masih sekitar 80%. Saya menambahkan beberapa unit test ke `ProductRepositoryTest` dan `ProductServiceImplTest` untuk menguji kinerja layer repository dan service jika diberikan input yang tidak valid. Akan tetapi, saya belum menambahkan unit test pada layer controller sebab menurut saya, library HTTP Java cukup sulit untuk dipahami.

2. Tidak, kualitas codenya seharusnya sama dengan functional test suite yang sudah ada sebelumnya. Dalam menulis test suite, saya cenderung mengikuti convention dan menggunakan syntax yang sudah ada sebelumnya sehingga prinsip Clean Code dapat diterapkan. Dalam menulis Clean Code, tentunya ada beberapa hal yang perlu diperhatikan, seperti penamaan variabel, method, dan function yang jelas, function/method melakukan satu pekerjaan saja, penerapan <i>separation of concerns</i>, serta menggunakan Exception/Error daripada return value untuk menyatakan sebuah flow yang tidak diinginkan dalam program.
