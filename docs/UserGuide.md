# User Guide

* [Quick Start](#quick-start)
* [Features](#features)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough. <br>
   This app will not work with earlier versions of Java 8.
   
1. Download the latest `addressbook.jar` from the [releases](../../../releases) tab.
2. Copy the file to the folder you want to use as the home folder for your Address Book.
3. Double-click the file to start the app. The GUI should appear in a few seconds. 
   > <img src="images/Ui.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window. 
5. Some example commands you can try:
   * **`add`**` add CS2103 tutorial 3 by/ Saturday` : 
     adds a task with the title of `CS2103 tutorial 3` to the FlexiTrack.
   * **`delete`**` 3` : deletes the 3rd contact shown in the current list
   * **`exit`** : exits the app
6. Refer to the [Features](#features) section below for details of each command.<br>


## Features

> **Command Format**
> * Words in `square brackets ([])` are the parameters.
> * Items within `arrow signs (<>)` are optional.
> * Items with `...` after them can have multiple instances.
> * The order of parameters is fixed.

#### Viewing help : `help`
Format: `help`

> Help is also shown if you enter an incorrect command e.g. `abcd`
 
#### Adding a task: `add`
Adds a task to the FlexiTrack.<br>
Format: `add [task title] < by/ [deadline] >`

Examples: 
* `add CS2103 tutorial 3 by/ Saturday`
* `add CS2103 tutorial 3 by/ 22 Oct`

#### Adding an event: `add`
Adds a task to the FlexiTrack.<br>
Format: `add [event title] from/ [starting time] to/ [ending time]`

Examples: 
* `add Bintan trip from/ Saturday to/ Sunday`
* `add CS2103 Lecture from/ Friday 2pm to/ 4pm `

#### Adding or changing deadline of a task : `deadline`
Add or change the deadline of a task in FlexiTrack.<br>
Format: `deadline [list number] [new deadline]`

Examples: 
* `deadline 3 10 October`
* `deadline 12 Friday 5pm`

#### Finding a task or an event containing any keyword in their title: `find`
Finds a task ot an event whose title contain any of the given keywords.<br>
Format: `find KEYWORD [MORE_KEYWORDS]`

> * The search is case sensitive. e.g `soccer` will not match `Soccer`
> * The order of the keywords does not matter. e.g. `soccer dinner` will match `dinner soccer`
> * Only the task/event title is searched.
> * Only full words will be matched e.g. `socc` will not match `soccer`
> * Task or event matching at least one keyword will be returned (i.e. `OR` search).
    e.g. `soccer` will match `soccer training`

Examples: 
* `find Soccer`<br>
  Returns `Soccer training` but not `soccer training`
* `find assignment dinner mid-term`<br>
  Returns Any task/event having title `assignment`, `dinner`, or `mid-term`

#### Deleting a person : `delete`
Deletes the specified task/event from the FlexiTrack. Irreversible.<br>
Format: `delete [index]`

> Deletes the task/event at the specified `index`. 
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...

Examples: 
* `delete 2`<br>
  Deletes the 2nd task/event in the address book.
* `find soccer`<br> 
  `delete 1`<br>
  Deletes the 1st task/event in the results of the `find` command.

#### Undo operations : `undo`
Undo operation a number of times.<br>
Format: `undo < [number of undo] >`

> The number of undo parameter is optional. When it is not satisfied, one undo will be done. 
> The maximum number of undo is 15 

Examples: 
* `undo`<br>
  Undo the operation 1 time.
* `undo 4`<br>
  Undo the operations 4 times. 
  
#### Mark a task as complete : `mark`
Mark an existing task to complete and move it to the bottom of the list.<br>
Format: `mark [index]`  

> Mark the taks/event at the specified `index`. 
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...
> The marked task will be deleted once the user exit the program 

Examples: 
* `mark 5`<br>	

#### Specify storage location: `storage`
Specify the storage location where the program save the data. <br>
Format: `storage [path]`  

Examples: 
* `storage C:/Document/Personal/Others `<br>	

#### Block multiple time slot for an event : `block`
Block another time slot for an unconfirmed existing event.<br>
Format: `block [starting time] to/ [ending time] for/ [index]`  

> Deletes the task/event at the specified `index`. 
  The index refers to the index number shown in the most recent listing.<br>
  The index **must be a positive integer** 1, 2, 3, ...

Examples: 
* `block Monday 3pm to/ 5pm for/ 3`<br>	

#### Find free time slot: `find time`
Find and list free time slot in the schedule that is equal to or longer than the specified timing (in hours).<br>
Format: `find time [number of hours] < [number of slots to find] >`  

> If there is there is a time slot longer than the required free time slot, 
	then the free time period will be return to the user
> By default, find time will only give a single free slot when the number of slots required is not keyed in.

Examples: 
* `find time 3 `<br>	
	You have a minimum of 3 hours free time slot between: today 5pm - 9pm. 
* `find time 5 3 `<br>	
	You have a minimum of 5 hours free time slot between: Monday 2pm - 9pm, Tuesday 1pm - 6pm and Saturday 9am - 5pm. 

#### Use shortcut to key in tasks and events details: `shortcut`
Enable user to use a shorter command word.<br>
Format: `enable shortcut`
Format: `disable shortcut` 

> Existing Command Word 				Shortcut Command word
> add											a/
> by 											b/
> from											f/
> to											t/
> delete										d/
> mark 											m/
> storage 										s/ 
> block 										bk/
> find time 									ft/

#### Exiting the program : `exit`
Exits the program.<br>
Format: `exit`  

#### Saving the data 
Address book data are saved in the hard disk automatically after any command that changes the data.<br>
There is no need to save manually.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with 
       the file that contains the data of your previous FlexiTrack folder.
       
## Command Summary

Command | Format  
-------- | :-------- 
Add task | `add [task title] < by/ [deadline] >`
Add event | `add [event title] from/ [starting time] to/ [ending time]`
Deadline | `deadline [index] [new deadline]`
Delete | `delete INDEX`
Find | `find KEYWORD [MORE_KEYWORDS]`
Undo | `undo [number of times]`
Mark | `mark [index]`
Storage | `storage [path]`
Find time | `find time [number of hours] < [number of slots to find] >`
Block | `block Monday 3pm to/ 5pm for/ 3`
