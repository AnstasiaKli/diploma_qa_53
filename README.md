# Дипломный проект по профессии «Тестировщик»

### Дипломный проект представляет собой автоматизацию тестирования приложения, которое взаимодействет с СУБД и API Банка.
Приложение - это веб-сервис, предлагающий купить тур по дебетовой карте или в кредит http://localhost:8080,

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
* сервису платежей (далее - Payment Gate)
* кредитному сервису (далее - Credit Gate)

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.

## Документация
* [План автоматизации тестирования](https://github.com/AnstasiaKli/diploma_qa_53/blob/main/docs/Plan.md)
* [Отчётные документы по итогам тестирования](https://github.com/AnstasiaKli/diploma_qa_53/blob/main/docs/Report.md)
* [Отчётные документы по итогам автоматизации](https://github.com/AnstasiaKli/diploma_qa_53/blob/main/docs/Summary.md)

### Настройка окружения
1. Открыть код программы в IDEA.
2. Запустить контейнеры с MySql, PostgreSQL и Node.js (для их запуска установлен Docker) в терминале с помощью команды docker-compose up --build.
3. Запустить тестируемый сервис в терминале:
* для работы с MySQL с помощью команды  java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar;
* для работы с Postgres с помощью команды  java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar;
4. Запустить тесты в терминале:
* для работы с MySQL с помощью команды ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
* для работы с Postgres с помощью команды ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"

### Формирование отчетов Allure
для получения отчета использовать команду ./gradlew allureServe
