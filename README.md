# KrosovokDeath
Плагин для Minecraft-серверов. Логирует смерти игроков в чат (опционально) и в файл `KrosovokDeath/logs/deaths.log`.
Настраиваемый конфиг и команды.

## Установка
1. Скачай `.jar` файл из https://spigotmc.ru/resources/krosovokdeath-nachni-logirovat-smerti-igrokov-uzhe-sejchas.3382/
2. Помести в папку `plugins` сервера.
3. Перезапусти сервер.

## Использование
- Логирование смертей: в чат (если включено) и в `logs/deaths.log`.
- Настройка в `config.yml`: цвет и текст сообщений, формат логов (компактный/вертикальный), команды после смерти (например, кик или удаление из вайтлиста с задержкой).
  
## Команды и права
### Команды
- `/kdeath reload` — Перезагрузить конфиг
- `/kdeath toggle` — Вкл/выкл уведомления в чате
- `/kdeath help` — Информация о плагине

### Права
- `krosovokdeath.notify` — Получать уведомления о смертях
- `krosovokdeath.toggle` — Переключать уведомления
- `krosovokdeath.reload` — Перезагружать конфиг

## Конфигурация
- Формат логов: компактный (`compact`) или вертикальный (`vertical`).
- **Примеры логов**:
  - Компактный: ![image](https://github.com/user-attachments/assets/b2c769b5-236a-4009-809e-399db8d82ae0)


  - Вертикальный: ![image](https://github.com/user-attachments/assets/b104cde4-a5b6-4e0a-a52f-a4ec01136bcc)


- Настройка текста, цвета, сообщений и задержку команд в `config.yml`.

## Зависимости
Отсутствуют  

## Лицензия
MIT

## Контакты
- Разработчик: https://t.me/krosov_ok
- Телеграмм канал: https://t.me/programsKrosovok
