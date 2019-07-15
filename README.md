# unusual-audio

## Setup
On MacOS, run:
```bash
brew install clojure leiningen postgresql
```

Then, to start the app, open two tabs in your terminal:
- For the backend:
```bash
lein brevity migrate
lein run
```

- For the frontend:
```bash
lein figwheel dev
```

## Guide

### Command-line interface

To add a regular user:
```bash
lein brevity user:new
```

To add an admin:
```bash
lein brevity user:new --admin
```

You can also pass user data in as command arguments if you don't want to be prompted for them:
```bash
lein brevity user:new -e non-admin@example.com -n "User's full name"
```

### Database migrations

All table definitions live in `unusual-audio/resources/private/migrations`, where each migration has a corresponding `.up.sql` and `.down.sql` file.  When you want to make a change to unusual-audio's schema, such as to add a table for storing comments:
```bash
lein brevity migrate:new -n basic-comment-table
```
This will put empty files, named something like `20180716210558-basic-comment-table.down.sql` and `20180716210558-basic-comment-table.up.sql`, in  `unusual-audio/resources/private/migrations/`.  Next, open the `.up.sql` file and type in your table definitions:
```sql
create table comments (
  id bigserial primary key,
  comment text not null,
  date_added timestamp default now()
);
```

Then in `.down.sql`:
```sql
drop table comments;
```

Then to run this migration, simply execute `lein brevity migrate` again.  You should also run `lein brevity migrate` after pulling or changing branches.

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
