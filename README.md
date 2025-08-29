# Football World Cup Score Board

## Overview

This project provides a service for managing a football game scoreboard supporting the following operations:
- Start a new game between two teams
- Finish an existing game
- Update the score of a game
- Get a summary of games
## Assumptions

- Team names must be non-null, non-empty, and cannot consist only of whitespace.
- Attempting to start a duplicate game (same home and away teams) throws `GameAlreadyStartedException`.
- Operations on games not present in the scoreboard throw `GameNotFoundException`.
- Scores must be non-negative integers.
- All games are stored in memory (`GameRepository`); there is no database.
- Game lookup requires exact match of both team names.

## Technical Notes

- The logic for storing and finding games is separated into a repository class (`GameRepository`)
- All public methods in `BoardService` validate input and handle exceptions according to business rules.
- Returned lists are copies and cannot be used to modify internal state.
- The solution is memory-based; data is lost when the application stops.
