
# Нагрузочное тестирование системы выдачи микрозаймов

## 1. Описание проекта

Проект представляет собой заглушку, эмулирующую работу системы процессинга в предметной области "Выдача микрозаймов".  
Заглушка предназначена для нагрузочного тестирования системы выдачи микрокредитов.

---


## 2. Команды для запуска
Для запуска заглушки необходима java версии 21 и выше.
Далее открыть терминал в директории проекта.
Например: 
```bash
С:\Users\Ivan> D:
D:\> cd Projects\
```
После открытия директории куда вы скачали репозиторий, пишем 
```bash
D:\Projects> cd firstSub\firstSub\target
```
Дальше в зависмости от профиля выбирем команды


### 2.1. Без указания профиля

```bash
java -jar -Xms1024m -Xmx2048m -jar firstSub-0.0.1-SNAPSHOT.jar
```
📍 Порт: 8080 

📍 Эмуляция задержки: 1 секунда

### 2.2. ИЛИ Запуск с профилем test1
```bash
java -Dspring.profiles.active=test1 -jar -Xms1024m -Xmx2048m -jar firstSub-0.0.1-SNAPSHOT.jar
```
📍 Порт: 7770 

📍 Эмуляция задержки: 2 секунды

### 2.3. ИЛИ Запуск с профилем test2
```bash
java -Dspring.profiles.active=test2 -jar -Xms1024m -Xmx2048m -jar firstSub-0.0.1-SNAPSHOT.jar
```
📍 Порт: 7775 

📍 Эмуляция задержки: 4 секунды

## 📡 3. API Эндпоинты 
🔹 1. Проверка информации о задолженностях

**GET /v2/checkAccount?acc={номер_аккаунта}&days={дни}**

Пример ответа (HTTP 202):
```json
{
  "account": "1234567890",
  "vip-client": false,
  "blocked": false,
  "inn": "1234567890111",
  "debt": [
    { "sum": 7654, "description": "phone" },
    { "sum": 1087, "description": "trip" }
  ]
}
```


🔹 2. Подтверждение платежа

**POST /v2/payment/**

Обязательный заголовок:
```json
BankCode: 22 //Любое число
```
Тело запроса:
```json
{
  "transaction_id": "D643-E",
  "sum": 964.1,
  "need_processing": true
}
```
Тело ответа (HTTP 200): 

Массив telecom содержит в ответе строк равное сумме цифр BankCode
```json
{
  "transaction_id": "D643-E",
  "bank_bik": "2345678997",
  "status": "accepted",
  "contact": [
    {
      "name": "HL pay company",
      "telecom": ["43t5h7", "k8fg534", "67hr333f", "gj6iire"]
    }
  ]
}
```
Заголовок ответа:
```json
ProcessingTime: 19-10-2025 14:33:41
```
🔹 3. Список платежей

**GET /v2/payment/**

Тело ответа (HTTP 200)
```json
[
  {
    "transaction_id": "7687564",
    "bank_bik": "89802623516",
    "status": "accepted",
    "contant": {
      "name": "HL pay company",
      "telecom": [
        "js124",
        "w9km",
        "ozkue9",
        "9whwl1"
      ]
    }
  },
  {
    "transaction_id": "54656434",
    "bank_bik": "98199346935",
    "status": "accepted",
    "contant": {
      "name": "HL pay company",
      "telecom": [
        "pbrs",
        "buuw",
        "fficz",
        "rh9p"
      ]
    }
  }
]
```

🔹 4. Удаление платежа по id

**DELETE /v1/transactions/cleare/{id}**

Тело ответа (HTTP 200):
```json
deleted success
```

## 📡 4. Документация Swagger 
Swagger находится по пути:

**/swagger-ui/index.html**

<div align="center">
    <img src="https://github.com/user-attachments/assets/b911c773-9ba5-4ab0-b96e-efc4c99cc3d8"/>
</div>

## 📡 5. База данных H2

**/h2-console/**

user - sa

passwoed - password

<div align="center">
    <img src="https://github.com/user-attachments/assets/579ea212-1edc-4668-ba3c-2029ad16ae57"/>
</div>

## 🪵 6. Логирование
Все вызовы API записываются в лог-файл, для профиля по умолчанию

Путь до лог файла:
```bash
./firstSub/firstSub/logs/app.logs
```
>>>>>>> master
