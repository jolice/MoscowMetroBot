# Moscow Metro Bot

A Telegram bot for planning routes in the Moscow Metro.

[![Build Status](https://travis-ci.org/riguron/MoscowMetroBot.svg?branch=master)](https://travis-ci.org/riguron/MoscowMetroBot)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/369ed18d70fd4215926ea0d5fa1bebbe)](https://www.codacy.com/manual/riguron/MoscowMetroBot?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=riguron/MoscowMetroBot&amp;utm_campaign=Badge_Grade)
[![HitCount](http://hits.dwyl.io/riguron/MoscowMetroBot.svg)](http://hits.dwyl.io/riguron/MoscowMetroBot)

# Running

To build the project and start the bot, use:

```
git clone git@github.com:riguron/MoscowMetroBot.git
cd MoscowMetroBot
mvn clean install && mvn spring-boot:run -pl Bootstrap -Dbot.token=YOUR_BOT_TOKEN -Dbot.username=YOUR_BOT_USERNAME
```

Do not forget to specify your bot's token and username.

# Features

- Construction of detailed text routes between any two stations in the Moscow Metro.
- Prefix-based station name matching eliminating the need for specifying full station name.
- Maintenance of statistics for the individual routes and stations.

# Commands

Besides the route planning, the bot currently supports the following commands:

- top: displays stations that are most commonly used in the routes.
- stats: tells the statistics of a route (how many times it was built) or a station (how many routes involve this station).

A command must start with the slash.

# Technologies used:

- Spring Boot
- Spring Data
- Hibernate 5
- TelegramBots
- Apache Commons Collections
- Google Gson
- HSQL Database
- Lombok
- Maven
