# ISA2020 - T14
Internet software architecture
--------------------------------

The main purpose of the application is to keep records
of Pharmacy Employees, registered Pharmacies, drug reservations, scheduling appointments
with Pharmacists and Dermatologists, managing Users and their records.


| Students | Team 14 |
|--------------|-------------|
| Student 1 | Stefan Arađanin |
| Student 2 | Danica Vojvodić |
| Student 3 | Jelena Vlajkov |
| Student 4 | Aleksandar Ignjatijević |

## Jobs done for this deadline:

Student 4 has done his tasks that are meeting criteria for the lowest passing grade. With the code there is also a class diagram done in Power designer. Student 4 can only guarantee for his part of the model in the class diagram, while the rest has been done using a mixture of the first draft of the model and with classes later modified by other students before they gave up. Model in pdf format is located in class_diagram floder.

## How to run


We are using:
- [Oracle JDK 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
  - [How to set JDK version on Windows/Linux/Mac](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux)


**Frontend**  
``` bash
    # From frontend/ run:
    npm install
    ng serve --o
```
**Backend** 

After cloning project from github, first you need to set windows system variables JDBC_DATABASE_URL, JDBC_DATABASE_USERNAME, JDBC_DATABASE_PASSWORD with your local Postgres configurations. 

After creating your local database, you will need to connect database source to your IntelliJ instance. Set up dataSource with your local variables and run Spring application. Before using application you will need to seed data with data-seeder.sql script. You can do that also from IntelliJ. 

``` bash
    Run as maven project
    Before using application use data-seeder.sql to seed data. 
```

 




## Emoji guideline
It is recommended to use emoji's where adequate when commiting  

See also [gitmoji](https://gitmoji.carloscuesta.me/).



|   Commit type              | Emoji                                         |
|:---------------------------|:----------------------------------------------|
| Initial commit             | :tada: `:tada:`                               |
| Version tag                | :bookmark: `:bookmark:`                       |
| New feature                | :sparkles: `:sparkles:`                       |
| Bugfix                     | :bug: `:bug:`                                 |
| Metadata                   | :card_index: `:card_index:`                   |
| Documentation              | :books: `:books:`                             |
| Documenting source code    | :bulb: `:bulb:`                               |
| Performance                | :racehorse: `:racehorse:`                     |
| Cosmetic                   | :lipstick: `:lipstick:`                       |
| Tests                      | :rotating_light: `:rotating_light:`           |
| Adding a test              | :white_check_mark: `:white_check_mark:`       |
| Make a test pass           | :heavy_check_mark: `:heavy_check_mark:`       |
| General update             | :zap: `:zap:`                                 |
| Improve format/structure   | :art: `:art:`                                 |
| Refactor code              | :hammer: `:hammer:`                           |
| Removing code/files        | :fire: `:fire:`                               |
| Continuous Integration     | :green_heart: `:green_heart:`                 |
| Security                   | :lock: `:lock:`                               |
| Upgrading dependencies     | :arrow_up: `:arrow_up:`                       |
| Downgrading dependencies   | :arrow_down: `:arrow_down:`                   |
| Lint                       | :shirt: `:shirt:`                             |
| Translation                | :alien: `:alien:`                             |
| Text                       | :pencil: `:pencil:`                           |
| Critical hotfix            | :ambulance: `:ambulance:`                     |
| Deploying stuff            | :rocket: `:rocket:`                           |
| Fixing on Windows          | :checkered_flag: `:checkered_flag:`           |
| Work in progress           | :construction:  `:construction:`              |
| Adding CI build system     | :construction_worker: `:construction_worker:` |
| Removing a dependency      | :heavy_minus_sign: `:heavy_minus_sign:`       |
| Adding a dependency        | :heavy_plus_sign: `:heavy_plus_sign:`         |
| Docker                     | :whale: `:whale:`                             |
| Configuration files        | :wrench: `:wrench:`                           |
| Package.json in JS         | :package: `:package:`                         |
| Merging branches           | :twisted_rightwards_arrows: `:twisted_rightwards_arrows:` |
| Bad code / need improv.    | :hankey: `:hankey:`                           |
| Reverting changes          | :rewind: `:rewind:`                           |
| Breaking changes           | :boom: `:boom:`                               |
| Code review changes        | :ok_hand: `:ok_hand:`                         |
| Accessibility              | :wheelchair: `:wheelchair:`                   |
| Move/rename repository     | :truck: `:truck:`                             |
