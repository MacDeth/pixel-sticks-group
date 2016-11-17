	!Programming Assignment 3
	!Pixel Sticks
	!Taylor Miller, James D'Angelo, Drew Richardson
	!
	PROGRAM main
	! local variables

	type :: Player
	   character(50) :: FirstName
	   character(50) :: LastName
	   Integer, dimension(10) ::  frameScores 
	   Integer, dimension(21) ::  ballsThrown
	end type Player
	
	integer, dimension(:), allocatable :: balls1to9
	integer, dimension(:), allocatable :: balls10
	integer :: frameNumber
	integer :: playerNumber
	integer :: fNum
	
	character(len=256) :: F 
	integer :: cnt
	integer :: n
	type(player), dimension(:), allocatable :: players
	
	print *, "Please enter the name of the input file" 
		read *, F 
	 open(unit= 1, file=F, status = "old", action = "read", iostat = ioerr)
		do while (ioerr /= 0)
            print *, "OPEN FAILURE, TRY AGAIN"
			read *, F
			open(unit= 1, file=F, status = "old", action = "read", iostat = ioerr)
		end do
         !if (ioerr == 0) then
         !  print *, "open successful"
		 !end if
		

	!get number of players
	read(1, *) n
	
	!create players array
	allocate(players(n))
	allocate(balls1to9( n * 2))
	allocate(balls10( n * 3))
	
	do i = 1, size(players)
		do j = 1, 10
			players(i)%frameScores(j) = 0
		end do
		do k = 1, 21
			players(i)%ballsThrown(k) = 0
		end do
	end do
	
	!Read In First and last name
	do j = 1, n
		read(1, *) players(j)%FirstName, players(j)%LastName
	end do
	
	j = 0
	
	! go through the frames 1 - 9
	frameNumber = 0
	
	do frameNumber = 1, 9
		read(1, *) fNum, balls1to9
		playerNumber = 0
		do playerNumber = 1, n
			call storeBallsThrown(players, playerNumber, frameNumber, balls1to9(playerNumber * 2 - 1), balls1to9(playerNumber * 2))
			call calculateFrameScore(players, playerNumber, frameNumber)
	   end do
	end do

	!frame 10
	playerNumber = 0
	cnt = 1
	read(1, *, iostat = ioerr) fNum, balls10
	do playerNumber = 1, n
		call storeBallsThrown(players, playerNumber, 10, balls10(cnt), balls10(cnt+1))
				
		cnt = cnt + 2
		
		!if strike or spare, read in 3rd ball
		if(players(playerNumber)%ballsThrown(19) + players(playerNumber)%ballsThrown(20) >= 10) then
			players(playerNumber)%ballsThrown(21) = balls10(cnt)
			cnt = cnt + 1
		end if
		call calculateFrameScore(players, playerNumber, 10)
	end do
	
1000	format(a20, a20, a5, i3, a5, i3, a5, i3, a5, i3, a5, i3, a5, i3, a5, i3, a5, i3, a5, i3, a5, i3, a3)
1001	format(a20, a20, i5, i3, i5, i3, i5, i3, i5, i3, i5, i3, i5, i3, i5, i3, i5, i3, i5, i3, i5, i3, i3)
	
	write(*, 1000) 'Name', '', '', 1, '', 2, '', 3, '', 4, '', 5, '', 6, '', 7, '', 8, '', 9, '', 10, ''

	playerNumber = 0
	do playerNumber = 1, n
	write(*, 1001) players(playerNumber)%firstName, players(playerNumber)%lastName, &
			players(playerNumber)%ballsThrown(1), players(playerNumber)%ballsThrown(2), & 
			players(playerNumber)%ballsThrown(3), players(playerNumber)%ballsThrown(4), &
			players(playerNumber)%ballsThrown(5), players(playerNumber)%ballsThrown(6), &
			players(playerNumber)%ballsThrown(7), players(playerNumber)%ballsThrown(8), &
			players(playerNumber)%ballsThrown(9), players(playerNumber)%ballsThrown(10), &
			players(playerNumber)%ballsThrown(11), players(playerNumber)%ballsThrown(12), &
			players(playerNumber)%ballsThrown(13), players(playerNumber)%ballsThrown(14), &
			players(playerNumber)%ballsThrown(15), players(playerNumber)%ballsThrown(16), &
			players(playerNumber)%ballsThrown(17), players(playerNumber)%ballsThrown(18), &
			players(playerNumber)%ballsThrown(19), players(playerNumber)%ballsThrown(20), &
			players(playerNumber)%ballsThrown(21)
	
		write(*, 1000) '', '', &
			'', players(playerNumber)%frameScores(1), '', players(playerNumber)%frameScores(2), &
			'', players(playerNumber)%frameScores(3), '', players(playerNumber)%frameScores(4),  &
			'', players(playerNumber)%frameScores(5), '', players(playerNumber)%frameScores(6),  &
			'', players(playerNumber)%frameScores(7), '', players(playerNumber)%frameScores(8),  &
			'', players(playerNumber)%frameScores(9), '', players(playerNumber)%frameScores(10), ''
		
		write(*, *) ''
	end do
	
	contains
		subroutine calculateFrameScore(players, playerNum, frameNum)
			!declare parameters
			type(Player), dimension(:), intent(inout) :: players
			Integer, intent(in):: playerNum, frameNum
			
			!variables for ball 1 and ball 2 in the frame (and ball3 in 10th frame)
			Integer :: ball1, ball2, ball3
			
			!variables to help with scoring strikes and spares
			Integer :: next1, next2
			
			!need a temp variable
			Integer :: temp
			
			!need a shorter variable name for playerNum
			Integer :: p
			p = playerNum
			i = 0
			!go through all frames up to this point and try to calculate the score if it doesn't have one
			do i = 1, frameNum
				!check to see if the current frame i does not have a score
				if(players(playerNum)%frameScores(i) == 0) then
					ball1 = players(playerNum)%ballsThrown((i*2)-1)
					ball2 = players(playerNum)%ballsThrown(i*2)
					
					!if first ball was 10- strike was rolled, so we need to get the next two balls thrown
					if(ball1 == 10) then
						!get ball 1 from next frame
						next1 = players(playerNum)%ballsThrown((i+1)*2-1)
						
						!if the next ball thrown was a strike, we need to go two frames ahead to get first ball thrown
						if(next1 == 10) then
							if(i == 9) then 
								next2 = players(playerNum)%ballsThrown(20)
								temp = players(playerNum)%frameScores(8)
								players(playerNum)%frameScores(i) = temp + 10 + next1 + next2
							else
								next2 = players(playerNum)%ballsThrown((i+2)*2-1)
							end if
						!if the next ball thrown was not a strike, get 2nd ball from next frame
						else
							next2 = players(playerNum)%ballsThrown((i+1)*2)
						end if
						!now check to see the i+1 and i+2 frame has been rolled yet
						if((i+1) <= frameNum) then
							if(next1 < 10 .or. (i+2) <= frameNum) then
								!if not in 1st frame, set current frame = previous frame
								if(i > 1) then
									players(playerNum)%frameScores(i) = players(playerNum)%frameScores(i-1)
								!if is 1st frame, set score = 0
								else
									players(playerNum)%frameScores(i) = 0
								end if
								!add 10 + next two balls onto score	
								temp = players(playerNum)%frameScores(i)
								players(playerNum)%frameScores(i) = temp + 10 + next1 + next2
							end if
						end if
					!if ball1 + ball2 = 10, we have a spare, so need to get the next ball thrown
					else if(ball1 + ball2 == 10) then
						next1 = players(playerNum)%ballsThrown((i+1)*2-1)
						!now check to see if i+1 frame has even been rolled yet
						if((i+1) <= frameNum) then
							!if not in 1st frame, set current frame = previous frame
							if(i > 1) then
								players(playerNum)%frameScores(i) = players(playerNum)%frameScores(i-1)
							!if is 1st frame, set score = 0
							else
								players(playerNum)%frameScores(i) = 0
							end if
							!add 10 + next ball onto score	
							temp = players(playerNum)%frameScores(i)
							players(playerNum)%frameScores(i) = temp + 10 + next1
						end if
					!no strike or spare
					else
						!if not in 1st frame, set current frame = previous frame
						if(i > 1) then
							players(p)%frameScores(i) = players(p)%frameScores(i-1)
						!if is 1st frame, set score = 0
						else
							players(p)%frameScores(i) = 0
						end if
						!add ball1 and ball2 to score
						temp = players(p)%frameScores(i)
						players(p)%frameScores(i) = temp + ball1 + ball2
					end if
				end if
			end do
			
			!check frame 10 strike or spare
			if(frameNum == 10) then
				ball1 = players(playerNum)%ballsThrown(19)
				ball2 = players(playerNum)%ballsThrown(20)
				ball3 = players(playerNum)%ballsThrown(21)
				
				!if strike
				if(ball1 == 10) then
					temp = players(playerNum)%frameScores(9)
					players(playerNum)%frameScores(10) = temp + 10 + ball2 + ball3
				!if spare
				else if(ball1 + ball2 == 10) then
					temp = players(playerNum)%frameScores(9)
					players(playerNum)%frameScores(10) = temp + 10 + ball3
				end if
			end if
		end subroutine calculateFrameScore
		
		subroutine storeBallsThrown(players, playerNum, frameNum, b1, b2)
			!declare parameters
			type(Player), dimension(:), intent(inout) :: players
			Integer, intent(in):: playerNum, frameNum, b1, b2
			
			!store ball1 and ball2
			players(playerNum)%ballsThrown((frameNum*2)-1) = b1
			players(playerNum)%ballsThrown((frameNum*2)) = b2
		end subroutine storeBallsThrown

	
	
	end program main