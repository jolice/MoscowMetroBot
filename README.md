# MoscowMetroBot

A Telegram bot for planning routes in the Moscow Metro.

# Features

- Construction of detailed text routes between any two stations in the Moscow Metro.
- Prefix-based station name matching eliminating the need for specifying full station name.
- Maintenance of statistics for the individual routes and stations.

# Commandst

Besides the route planning, the bot currently supports the following commands:

- top: displays stations that are most commonly used in the routes
- stats: tells the statistics of a route (how many times it was built) or a station (how many routes involve this station)

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
