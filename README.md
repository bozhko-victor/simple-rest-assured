### Добавляем проверку на соответствие JSON схеме:

1. Создаем в директории resorses папку schemas и добавляем в нее JSON файл
2. Добавляем в тест проверку c указанием пути к этому файлу ``` .body(matchesJsonSchemaInClasspath("schemas/GenerateTokenSchema.json"))```

Вспомогательный ресурс (преобразуем 	
Response body из swagger в JSON) http://json-schema.org
