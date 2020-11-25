create table Choice (
	choiceID INT(16) not null,
	name_str VARCHAR(32) not null,
	description_str VARCHAR(100),
	maxMembers INT not null,
	date_completed date,
	constraint Choice_PK primary key (choiceID)
);

create table TeamMember (
	memberID INT(16) not null,
	name_str VARCHAR(32) not null,
	choiceID INT(16) not null,
	password_str VARCHAR(32),
	constraint Member_PK primary key (memberID),
	constraint Member_Team_FK foreign key (choiceID) references Choice (choiceID)
);

create table Alternative (
	alternativeID INT(16) not null,
	choiceID INT(16) not null,
	description_str VARCHAR(100) not null,
	selected boolean,
	constraint Alternative_PK primary key (alternativeID),
	constraint Alternative_Choice_FK foreign key (choiceID) references Choice (choiceID)
);

create table Vote (
	memberID INT(16) not null,
	alternativeID INT(16) not null,
	isUpvote boolean not null,
	constraint Vote_PK primary key (memberID, alternativeID),
	constraint Vote_Member_FK foreign key (memberID) references TeamMember (memberID),
	constraint Vote_Alternative foreign key (alternativeID) references Alternative (alternativeID)
);

create table Feedback (
	feedBackID INT(16),
	authorID INT(16) not null,
	alternativeID INT(16) not null,
	feedbackText VARCHAR(100),
	feedbackTime datetime,
	constraint Feedback_PK primary key (feedBackID),
    constraint Feedback_Member_FK foreign key (authorID) references TeamMember (memberID),
	constraint Feedback_Alternative_FK foreign key (alternativeID) references Alternative (alternativeID)
);