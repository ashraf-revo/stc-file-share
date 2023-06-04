package com.asrevo.stcfileshare;

public class Task2 {
    /**
     * query that return users who took the training lesson more than once
     */
    private final String query = """
            select u.user_id, u.user_name, td.traning_id, td.training_date, count(td.training_date)
            from public.user u
                     left join public.traning_details td on u.user_id = td.user_id
            group by u.user_id, u.user_name, td.traning_id, td.training_date
            having count(td.training_date) > 1
            order by td.training_date desc
            """;

}
