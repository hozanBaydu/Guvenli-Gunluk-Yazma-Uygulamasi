# Güvenli şifre kaydedici uygulaması
#### Hozan BAYDU

Merhaba,bu uygulamayı java dilinde,firebase kullanarak kullanıcıların şifrelerini kaydetip görüntüleyebilmesi için yaptım. 


## Özellikler

- Uygulama bilgileri firebasede depolar.
- Kullanıcılar istediği kadar şifre kaydedebilir.


### Giriş sayfası

![giriş sayfası](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEj1e_E0T6VK1hQFsj0e6yQw-j5bVEuH2HVoA-hi3o3X-KOv30FIH6J5XJbxpBuGRoWEIchdFsnE_dVxPKZav4qhFMGHtPK0KI5KCH3EVmGytxxGLBpupyRFpY7wRIz5LAU5YKyH7ZhqiwE5bfWOaT8ZQTmY0iXBW8o00MELefQeAb60XC4-g87Vqvt4/s600/parola4.jpeg)


```sh
public void giris(View view){

        String email = binding.emailText.getText().toString();
        String password = binding.sifreText.getText().toString();


        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(authResult -> {

            Intent intent = new Intent(MainActivity.this, PasswordActivity.class);
            startActivity(intent);
            finish();
        }).addOnFailureListener(e -> Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_SHORT).show());
    }
```

Giriş sayfasındaki email texten ve sifretextten gerekli girdileri aldıktan sonra bilgileri yukarıdaki kodlar sayesinde firebaseye yollayıp doğruluğu hakkında bilgi alınır.

```sh
firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Toast.makeText(MainActivity.this,"User Created",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this,PasswordActivity.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
```

Kullanıcı uygulamada yeni kayıt açtırmak istiyorsa bunu yukarıdaki kod yapar.
### PasswordActivity 


![giriş sayfası](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhOJZb350RccwyivUacgYnFUP7-xoJsR-km89jfVvsj2ttJQAmGqg7ud1DIJWXhLYEA7VRWcQKV0JBtSVqchECT8413KLltgVjjyMIJLRyvupxw_N_cMdzXiAcsfc4oDxWx05-CRQUBzHxKBdPghlxLxdz1P5PPW9N3ll3ifKm4JSA0DBiWzBP_UhPs/s600/parola3.jpeg)


Kullanıcı giriş yaptıktan sonra bilgilerin yer aldığı recyclerview bu aktivitede gösterilir.

```sh
String email= Objects.requireNonNull(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail()).toString();
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");


        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("useremail",email).addSnapshotListener(new EventListener<QuerySnapshot>() {
```


Email diye bir değişken tanımlayıp bilgileri bu emaile eşit olan verilerin çekilmesi için yukarıdaki kodları yazdım çünkü bunu yapmazsak kullanıcı uygulamada olan tüm verilere erişir.




### UploadActivity

![giriş sayfası](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhxFKZiBNADMI98cSfd3vHLDdOSh-J49-fVojVYxlTnTEPWM_A6iW4as7Ku9YfeeRRWQixZYn2VvLIlYjSk9OG5RNTp398B7_8b5GhEUD_59cK9IAh35y0JReidHkNCFj111MJi2jDmDoU0z3-T924NMCwenH6u8FXPOyQV8zvX17_mHSs8Y4ijRFPo/s600/parola1.jpeg)



Kullanıcı bu aktiviteden yeni parolalar kaydedebilir.

```sh
 FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userEmail = firebaseUser.getEmail();

        String nameText = binding.addNameText.getText().toString();
        String passwordText = binding.addPasswordText.getText().toString();

        HashMap<String, Object> postData = new HashMap<>();
        postData.put("useremail",userEmail);

        postData.put("name",nameText);
        postData.put("password",passwordText);
        postData.put("date", FieldValue.serverTimestamp());
```

Yukarıdaki kodlar Post koleksiyonuna name,password ve email diye 3 field altında bilgi kaydeder.
Bunları Stringe çevirip anahtar değer bilgi eşleşmesi için bir HashMap içine kaydettim.



### DetailsActivity
![giriş sayfası](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjBqc_-24JF4TGZiZFRgOya5X23rskzKWDGLxl5F5L8rO8usnhlQzv1AdcHejisqlenFi73M_aJKrPfWS8ykd08GODeNKf9fNdq76sm1cHTQOreH_DFehjJd3jLYBNhlvx1syA65Yg1mV4kTQW-2U-iNd6hErcum_6fLsRn2-ISoz7-7iDwRgbNQKUh/s600/parola2.jpeg)

```sh
holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailsActivity.class);
                intent.putExtra("password",passwordArrayList.get(position));

                holder.itemView.getContext().startActivity(intent);

            }
        });
```

Yukarıdaki adapter içinde yer alan kod,recyclerview içinde tıklanan itemin pozisyonunu alıp details aktivitesine intent yoluyla yollayıp details aktivitesinde gösterilecek olan bilgilerin ayrıntılarını almamıza yardımcı olması için yazıldı.

```sh
Intent intent = getIntent();
        Model passwordModel = (Model) intent.getSerializableExtra("password");

        binding.nameDetailsText.setText(passwordModel.name);
        binding.passwordDetailsText.setText(passwordModel.password);
```

Yukarıda da görüldüğü üzere gelen intenti alıp textviewde gösterdim.Bunların yapılabilmesi için modelin serileştirilebilmesi gerektiği için modeli aşağıdaki kodlarda görüldüğü gibi yazdım.

```sh
Intent intent = getIntent();
        public class Model implements Serializable {
    public String name;
    public String password;
    public String email;


    public Model(String name, String password,String email) {
        this.name = name;
        this.password = password;
        this.email=email;

    }
}

Uygulamayı yazan:Hozan BAYDU

Tasarım:Adobexd,Canva

Tarih:20.08.2022

iletişim:hozan.baydu3447@gmail.com
