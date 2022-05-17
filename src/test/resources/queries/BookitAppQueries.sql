SELECT firstname,lastname ,role
FROM users
WHERE email = '' ;

select t.name from users u inner join team t on u.team_id = t.id where u.email ='lebron6@gmail.com';

select b.number from users u inner join team on u.team_id = team.id inner join batch b on b.number = batch_number where firstname ='Magic';

select c.location from users u inner join team t on u.team_id = t.id inner join campus c on t.campus_id = c.id where u.email ='kd34@msu.edu';


SELECT *
from team
where team.name='PhantomPain';





