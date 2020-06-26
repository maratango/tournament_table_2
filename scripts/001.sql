create table games (
game_id     int not null,
home_team   varchar(100) not null,
home_goal   int not null,
guest_team  varchar(100) not null,
guest_goal  int not null
);

ALTER TABLE games
    ADD CONSTRAINT gamesPK PRIMARY KEY (game_id);
	
CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;

CREATE OR REPLACE VIEW turnTable AS
with
teams as
    (select team from
        (select home_team as team
        from games
        group by home_team
        union all
        select guest_team as team
        from games
        group by guest_team)
    group by team),
winers as
    (select team, sum(count) as count from
        (select home_team as team, count(*) as count
        from games where
        home_goal > guest_goal
        group by home_team
        union all
        select guest_team as team, count(*) as count
        from games where
        home_goal < guest_goal
        group by guest_team)
    group by team),
drawers as
    (select team, sum(count) as count from
        (select home_team as team, count(*) as count
        from games where
        home_goal = guest_goal
        group by home_team
        union all
        select guest_team as team, count(*) as count
        from games where
        home_goal = guest_goal
        group by guest_team)
    group by team),
losers as
    (select team, sum(count) as count from
        (select home_team as team, count(*) as count
        from games where
        home_goal < guest_goal
        group by home_team
        union all
        select guest_team as team, count(*) as count
        from games where
        home_goal > guest_goal
        group by guest_team)
    group by team),
goals_scored as
    (select team, sum(count) as count from
        (select home_team as team, sum(home_goal) as count
        from games
        group by home_team
        union all
        select guest_team as team, sum(guest_goal) as count
        from games
        group by guest_team)
    group by team),
goals_conceded as
    (select team, sum(count) as count from
        (select home_team as team, sum(guest_goal) as count
        from games
        group by home_team
        union all
        select guest_team as team, sum(home_goal) as count
        from games
        group by guest_team)
    group by team)
select
    t.team,
    (NVL(w.count, 0) * 3 + NVL(d.count, 0)) as point,
    NVL(w.count,0) as win,
    NVL(d.count,0) as draw,
    NVL(l.count,0) as lose,
    NVL(gs.count,0) as gs,
    NVL(gc.count,0) as gc,
    (NVL(gs.count,0) - NVL(gc.count,0)) as gd
from teams t
left join winers w on t.team=w.team
left join drawers d on t.team=d.team
left join losers l on t.team=l.team
left join goals_scored gs on t.team=gs.team
left join goals_conceded gc on t.team=gc.team
order by point desc, gd desc;