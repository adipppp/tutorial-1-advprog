# tutorial-advprog
Nama: Fernanda Nadhiftya Putra<br>
NPM: 2206081686

## Module 4
### Reflection
1. Dalam konteks Exercise 4 ini, proses TDD cukup rumit dan memakan banyak waktu, terutama saat pertama kali menulis test untuk sebuah class. Akan tetapi, saya merasa bahwa dengan adanya TDD, sebuah program dapat lebih mudah di-maintain bahkan ketika ada fitur-fitur baru yang ditambahkan pada program tersebut.

2. Test yang sudah saya tulis sudah berjalan secepat mungkin sehingga tidak akan menghambat workflow. Selain itu, test tersebut menghasilkan hasil yang konsisten, self-validating, tidak tergantung pada test case lain, serta mengcover happy dan unhappy path. Oleh sebab itu, saya menyimpulkan bahwa tests yang saya tulis sudah mengikuti F.I.R.S.T Principle.

## Module 3
### Reflection
1. Single Responsibility Principle pada project ini saya terapkan pada setiap method pada setiap class implementasi `Repository`, `Service`, dan `Controller`. Contohnya, method `findById()` pada class `CarRepository` hanya memiliki sebuah <i>responsibility</i>, yaitu mencari sebuah `Car` dalam repository.<br><br>
Open-Closed Principle pada project ini contohnya saya terapkan pada `ItemRepository`. `ItemRepository` dapat diimplement oleh dua class sekaligus, yaitu `CarRepository` dan `ProductRepository` tanpa harus memodifikasi interface tersebut.<br><br>
Liskov Substitution Principle saya terapkan pada `CarService` dan `CarServiceImpl`, dimana keduanya bisa dipakai dalam `CarController` dengan fitur yang sama.<br><br>
Interface Segregation Principle saya terapkan pada interface `ProductService` dan `CarService`, dimana `ProductService` digunakan secara khusus untuk `Product` serta `CarService` digunakan secara khusus untuk `Car`.<br><br>
Dependency Inversion Principle contohnya saya terapkan pada penggunaan `CarService` daripada `CarServiceImpl` sebagai service untuk `CarController`.<br><br>

2. Dengan menggunakan SOLID principle, codebase akan lebih mudah untuk di-maintain karena setiap class dan methodnya memiliki kegunaan dan fungsi yang jelas. Contohnya class `CarRepository` yang berfungsi untuk menghandle penyimpanan data `Car` serta method-methodnya seperti `deleteById()` yang melakukan sebuah <i>responsibility</i> yaitu menghapus data dari tempat penyimpanan.<br><br>

3. Tanpa SOLID principle, sebuah method `deleteById()` pada class `CarRepository` yang sudah disebutkan bisa saja memiliki fungsionalitas lebih dari satu. Selain itu, bisa saja `ProductServiceImpl` dan `CarServiceImpl` menggunakan sebuah interface besar yang sama. Hal ini tentu membuat tujuan dari setiap class maupun interface tidak terlalu jelas.

## Module 2
### Reflection
1. Pertama, ada masalah penggunaan `@Autowired` pada ProductController. `@Autowired` seharusnya diletakkan di atas constructor ProductController, bukan di atas field `service`. Saya memperbaikinya dengan menambahkan constructor baru, meletakkan `@Autowired` di atas constructor, kemudian mengassign field `service` pada constructor tersebut. Selain itu, ada juga issue yang melarang penulisan string yang sama berulang kali tanpa menggunakan constants. Oleh karena itu, saya mendeclare sebuah constant `PRODUCT_ATTR_NAME` pada ProductController untuk merepresentasikan string "product"

2. Menurut saya, workflow pada repository ini sudah masuk definisi Continuous Integration dan Continuous Deployment. Pada repository ini, Continuous Integration terletak pada workflow `ci.yml` dan `build.yml`. Kemudian, Continuous Deployment terletak pada deployment ke Koyeb. Saat ada pull request atau push, script yml tersebut akan menjalankan github actions, kemudian Koyeb akan mendeteksi adanya perubahan pada repository dan akan melakukan deployment secara otomatis.

URL koyeb: https://advshop-adipppp.koyeb.app/

## Module 1
### Reflection 1
Setelah mempelajari prinsip Clean Code, saya sebisa mungkin tidak menggunakan comments dalam menulis kode. Agar kode tetap mudah dipahami, saya memberikan nama yang deskriptif pada setiap variabel dan method yang saya tulis. Selain itu, saya menerapkan error handling pada kode saya. Saat sebuah method mengalami hal yang tidak diinginkan, saya segera melakukan throw `RuntimeException` dengan message yang menjelaskan mengapa error tersebut terjadi. Exception tersebut kemudian akan di-handle oleh method lain yang memanggil method tersebut.

Contohnya, saat `Product` yang dicari tidak dapat ditemukan di `ProductRepository`, sebuah `RuntimeException` akan di-throw pada method `findOne()` punya `ProductRepository` tersebut. Method `findOne()` punya `ProductService` yang memanggil method `findOne()` punya `ProductRepository` akan menghandle error tersebut dengan cara melakukan throw kembali. Selain itu, validasi input lain yang saya terapkan pada `ProductService` juga akan melakukan throw `RuntimeException` jika input yang diperoleh dari `ProductController` tidak valid. Exception yang di-throw dari method pada `ProductService` tersebut pada akhirnya akan di-handle oleh `ProductController`. `ProductController` akan menampilkan tampilan yang berbeda-beda sesuai dengan input yang diberikan. Jika input tidak valid, `ProductController` tentu akan mendapatkan exception dari `ProductService` sehingga `ProductController` akan memberi tahu user/client bahwa input yang dimasukkan invalid.

Dengan ini, saya menerapkan <i>separation of concerns</i> dalam menghandle sebuah exception.

### Reflection 2
1. Untuk branch `unit-test`, saya rasa <i>code coverage</i>nya masih sekitar 80%. Saya menambahkan beberapa unit test ke `ProductRepositoryTest` dan `ProductServiceImplTest` untuk menguji kinerja layer repository dan service jika diberikan input yang tidak valid. Akan tetapi, saya belum menambahkan unit test pada layer controller sebab menurut saya, library HTTP Java cukup sulit untuk dipahami.

2. Tidak, kualitas codenya seharusnya sama dengan functional test suite yang sudah ada sebelumnya. Dalam menulis test suite, saya cenderung mengikuti convention dan menggunakan syntax yang sudah ada sebelumnya sehingga prinsip Clean Code dapat diterapkan. Dalam menulis Clean Code, tentunya ada beberapa hal yang perlu diperhatikan, seperti penamaan variabel, method, dan function yang jelas, function/method melakukan satu pekerjaan saja, penerapan <i>separation of concerns</i>, serta menggunakan Exception/Error daripada return value untuk menyatakan sebuah flow yang tidak diinginkan dalam program.
