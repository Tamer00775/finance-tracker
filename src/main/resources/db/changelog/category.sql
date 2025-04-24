alter table category add column type varchar;

insert into category(ru, kk, code, type) values
                                             ('Покушать', '', 'pokushat', 'expense'),
                                             ('Продукты', '', 'produkty', 'expense'),
                                             ('Транспорт', '', 'transport', 'expense'),
                                             ('Развлечения', '', 'razvlecheniya', 'expense'),
                                             ('Кафе', '', 'kafe', 'expense'),
                                             ('Здоровье', '', 'zdorovye', 'expense'),
                                             ('Комуналка', '', 'komunalka', 'expense'),
                                             ('Спорт', '', 'sport', 'expense'),

                                             ('Подработка', '', 'podrabotka', 'income'),
                                             ('Зарплата', '', 'zarplata', 'income'),
                                             ('Фриланс', '', 'frilans', 'income'),
                                             ('Подарки', '', 'podarki', 'income');

