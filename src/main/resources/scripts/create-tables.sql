  create table answer (
       answer_type varchar(31) not null,
        id bigint not null,
        answer_index integer,
        text varchar(255),
        rating integer,
        question_id bigint,
        answer_section_id bigint not null,
        attachment_id bigint,
        primary key (id)
    ) type=MyISAM;

    create table answer_section (
       id bigint not null,
        response_section_index integer not null,
        response_id bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table answer_selection (
       answer_id bigint not null,
        selection_rank integer,
        selectionRanks_KEY integer not null,
        selection integer,
        primary key (answer_id, selectionRanks_KEY)
    ) type=MyISAM;

    create table file (
       id bigint not null,
        date datetime not null,
        fileData longblob,
        name varchar(255) not null,
        size bigint,
        type varchar(255),
        url varchar(255),
        owner_id bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table hibernate_sequence (
       next_val bigint
    ) type=MyISAM;

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    insert into hibernate_sequence values ( 1 );

    create table question (
       question_type varchar(31) not null,
        id bigint not null,
        description varchar(255) not null,
        number_of_selections integer default 4,
        ranking_scale integer default 1 not null,
        rating_scale integer not null,
        attachment_allowed boolean default false not null,
        text_length integer default 20,
        question_section_id bigint,
        question_index integer,
        primary key (id)
    ) type=MyISAM;

    create table question_choices (
       question_id bigint not null,
        choice varchar(3000),
        choice_index integer not null,
        primary key (question_id, choice_index)
    ) type=MyISAM;

    create table question_ranking_choices (
       question_id bigint not null,
        ranking_choice varchar(3000),
        ranking_choice_index integer not null,
        primary key (question_id, ranking_choice_index)
    ) type=MyISAM;

    create table question_section (
       id bigint not null,
        description varchar(255),
        survey_id bigint,
        section_index integer,
        primary key (id)
    ) type=MyISAM;

    create table survey (
       id bigint not null,
        close_date datetime,
        created_date datetime not null,
        deleted bit not null,
        description varchar(255) not null,
        name varchar(255) not null,
        publish_date datetime,
        type integer not null,
        author_id bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table survey_chart (
       id bigint not null,
        date datetime,
        deleted bit not null,
        name varchar(255) not null,
        x_label varchar(255),
        y_label varchar(255),
        y_max integer,
        y_min integer,
        creator_id bigint,
        primary key (id)
    ) type=MyISAM;

    create table survey_chart_point (
       id bigint not null,
        average double precision,
        max double precision,
        median double precision,
        min double precision,
        question_index integer not null,
        section_index integer not null,
        values_set bit not null,
        survey_id bigint,
        series_id bigint,
        point_index integer,
        primary key (id)
    ) type=MyISAM;

    create table survey_chart_serie (
       id bigint not null,
        name varchar(255),
        chart_id bigint,
        primary key (id)
    ) type=MyISAM;

    create table survey_chart_xcoordinate (
       chart_id bigint not null,
        coordinate varchar(255),
        coordinate_order integer not null,
        primary key (chart_id, coordinate_order)
    ) type=MyISAM;

    create table survey_responses (
       id bigint not null,
        survey_id bigint not null,
        primary key (id)
    ) type=MyISAM;

    create table user (
       id bigint not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id)
    ) type=MyISAM;

    alter table user 
       add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);

    alter table answer 
       add constraint FK8frr4bcabmmeyyu60qt7iiblo 
       foreign key (question_id) 
       references question (id);

    alter table answer 
       add constraint FK1jmd2cwyk9f0l6v6hhh28ahha 
       foreign key (answer_section_id) 
       references answer_section (id);

    alter table answer 
       add constraint FK8y73nf73k9d808k0c7wn3glm0 
       foreign key (attachment_id) 
       references file (id);

    alter table answer_section 
       add constraint FKb5nvaw1fvmk0nvmmecx2kaq0t 
       foreign key (response_id) 
       references survey_responses (id);

    alter table answer_selection 
       add constraint FK2m108eua3qjm6eptjf2qoexgv 
       foreign key (answer_id) 
       references answer (id);

    alter table file 
       add constraint FKlnhwmdni364jch8j374mwcui4 
       foreign key (owner_id) 
       references user (id);

    alter table question 
       add constraint FKaqddxaempr1yfudlve9rcsokg 
       foreign key (question_section_id) 
       references question_section (id);

    alter table question_choices 
       add constraint FKifc0cyjdk3ijjhtju0fual7a6 
       foreign key (question_id) 
       references question (id);

    alter table question_ranking_choices 
       add constraint FK1fpjmc9vql074od32k6bwgnsu 
       foreign key (question_id) 
       references question (id);

    alter table question_section 
       add constraint FK5ow64hpsjrr1k9gv9p0j0lrka 
       foreign key (survey_id) 
       references survey (id);

    alter table survey 
       add constraint FK1md5h129eoql7787hkxoe3hbs 
       foreign key (author_id) 
       references user (id);

    alter table survey_chart 
       add constraint FKmbwb3gn29w9h76hths6llk1uh 
       foreign key (creator_id) 
       references user (id);

    alter table survey_chart_point 
       add constraint FKn7upug6mtwdpgw2x9cgh7tcdh 
       foreign key (survey_id) 
       references survey (id);

    alter table survey_chart_point 
       add constraint FK6uwp18vk79somo2sg2jwc22c9 
       foreign key (series_id) 
       references survey_chart_serie (id);

    alter table survey_chart_serie 
       add constraint FK8set8syu5hrl8so8wjvn8qhg3 
       foreign key (chart_id) 
       references survey_chart (id);

    alter table survey_chart_xcoordinate 
       add constraint FKhwdbjagx81j02hwak4crs4sxe 
       foreign key (chart_id) 
       references survey_chart (id);

    alter table survey_responses 
       add constraint FKp0gml0ba3fdu10y089635nsii 
       foreign key (survey_id) 
       references survey (id);
