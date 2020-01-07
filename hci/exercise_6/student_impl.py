import sys
from time import strftime
from keras import Sequential, Model
from gym.spaces import Discrete
from keras.layers import Dense
from keras.optimizers import Adam


# Please fill this out with your information:
PLAYER_LASTNAME = "Verheyen"
MATRICULATIONNUMBER = "3043171"
STATE = dict(score=0)


def set_gape_size() -> str:
    """Sets the difficulty of the game. Must return a string.
    Return either `HARD` (100), `MEDIUM` (125) OR `EASY` (150)
    The numbers in the brackets are the gaps within the pipes"""
    diffs = ["HARD", "MEDIUM", "EASY"]
    return diffs[0]


def set_game_static_pipes() -> bool:
    """Return true here to have static pipes. Set to false to have dynamic pipes."""
    return False


def get_agent_action_space():
    """This implements the AI's action space. The bird has a discrete space, i.e. 0 or 1 - whether to flap or not.
    You usually should not need to edit this function."""
    return Discrete(2)


"""Documentation for the `data: dict`:

Some functions receive a big dictionary that is named `data`. Here you can access several variables of the game's state.
You can retrieve as much information from it as you like, but you must not modify it. This means, that all write access
is disallowed and will lead to disqualification.

Many information is stored in the dict that is annotated below. Notable keys are:
* 'game_object': This contains the whole game as an object of class FlappyGame (see file lib.environment.py)
                 Here, all logic of the game, collision detection, etc. is performed and implemented.
* 'score'      : This is the score of the game that is incremented, when the bird passes a gap.
* 'upperPipes' : The game knows to pipes at each time. Once the first pipe is passed, then the second pipe in the array
                 is the next pipe. You can use a simple example code (see draw_game_callback()) to calculate the next
                 pipe's index.
* 'lowerPipes' : (See above.)
* 'SCREEN'     : This is a pygame surface that can be drawn to. Useful if you want to visualize your sensing.
* 'crashed'    : This is true if the bird had a collision with any object.
* 'agent'      : This is an object of the class 'DQNAgent'. It holds a keras 'model' that you can access.

Here is an example for a `data` dictionary: 
    {
       'game_object':<lib.flappy.FlappyGame object at 0x00000193A22A1508>,      // main game object (type: FlappyGame)
                                                                                // contains the logic of the game
       'score':0,                                                               // current score in game
       'lowerPipes':[                                                           // next two lower pipe coords
          {
             'x':484,
             'y':233
          },
          {
             'x':628.0,
             'y':265
          }
       ],
       'upperPipes':[                                                           // next two upper pipe coords
          {
             'x':484,
             'y':-237
          },
          {
             'x':628.0,
             'y':-205
          }
       ],
       'player_x':0,                                                            // bird position, rotation and velocity
       'player_y':207.0,
       'player_rot':42,
       'player_vel_x':-4,
       'player_vel_y':1,
       'SCREEN':<Surface(288x512x32 SW)>,                                       // pygame-screen. Can be drawn to.
       'crashed':False,                                                         // True if bird crashed else false
       'where':'',                                                              // contains crash location
       'travelled_distance':1,                                                  // distance travelled by bird
       'steps':1,                                                               // steps of the game, i.e. frames
       'player_w':34,                                                           // bird sprite width
       'player_h':24,                                                           // bird sprite height
       'pipe_w':52,                                                             // pipe sprite width
       'pipe_h':320,                                                            // pipe sprite height
       'player_center_x':17.0,                                                  // center of bird x
       'player_center_y':219.0,                                                 // center of bird y
       'reward':0,                                                              // YOUR generated reward
       'observation':[                                                          // YOUR generated observation
          0
       ],
       'agent':<lib.ai.DQNAgent object at 0x00000193A220C408>                   // object of class 'DQNAgent'
                                                                                // Contains a keras model!
                                                                                // Here you can access avg_reward in the
                                                                                // agent, as well as avg_loss.
                                                                                // Both avg_reward and avg_loss contain
                                                                                // the average loss and reward for the
                                                                                // episode.
    }
"""

def get_agent_observation_space() -> int:
    """Set the dimension of the observation space.
    In get_agent_observation() a list of observations is returned.
    Here, you must note its dimension (e.g., the number of elements for a 1D-list).
    In case you encounter an error like this: `ValueError: cannot reshape array of size 3 into shape (1,1)`
    then you probably have not changed this function."""
    DIMENSION_OBSERVATION_SPACE = 1
    # TODO Edit me a change is done in `get_agent_observation`
    return DIMENSION_OBSERVATION_SPACE


def get_agent_observation(data: dict) -> list:
    """This function implements the sensing of the bird. The dict `data` provides you a lot of information.
    A small documentation on `data` can be found above. It is best to place a debugger on the first `pass` in
    your IDE (we recommend PyCharm, its free and open source) and check the given data. You are only allowed
    to read the data and you MUST NOT edit or alter it in any way (especially not the given GAME-object).
    In this function please return, whatever the bird *senses*. The output will be fed to the DQN for training..
    You can return as many variables as you like, but it must be a list and you must match the
    INPUT_DIMENSION_OBSERVATION_SPACE accordingly.
    """

    observation = []
    target_upper = get_next_target(data, target='upperPipes')
    target_lower = get_next_target(data, target='lowerPipes')

    # 1. the top of the bird should be at the middle of the gap
    target_y = target_upper['y'] + ((target_lower['y'] - target_upper['y']) / 2)
    distance = data['player_y'] - target_y
    observation.append(distance)

    return observation


def get_agent_reward_space() -> tuple:
    """This function implements the reward space, i.e. a tuple, that scales from a lower value to an upper value.
    The reward that is returned by get_agent_reward() should be within the bounds. You can also use np.inf or
    np.-inf."""
    my_tuple = (0, 1)
    return my_tuple


def get_agent_reward(data: dict) -> float:
    """This function is similar to get_agent_observation() and the same restrictions apply here.
    Here, return a reward that is calculated *per step* of the game. It *must* be a float.
    The idea is, to implement a reward that rewards the bird for *good* actions. (Whatever that means :^) )

    If you ever get the following exception:
        numpy.core._exceptions.UFuncTypeError: Cannot cast ufunc 'add' output from dtype('float64') to dtype('int32')
        with casting rule 'same_kind'
    Then you probably did not cast the return value to float.
    """
    global STATE
    reward = 0.01
    if data['score'] > STATE['score']:
        STATE['score'] = data['score']
        reward = 1
    if data['crashed']:
        reward = 0
    return reward


def get_agent_parameters() -> dict:
    """Here, you can fine tune the network's parameters. They are utilized in the DQNAgent-class in lib.ai.py"""
    return {'learning_rate': 0.0005,
            'gamma': 1.0,
            'replay_memory': 50000,
            'replay_size': 128,
            'epsilon': .2,
            'epsilon_decay': 0.995,
            'epsilon_min': 0.00001,
            'episode_b4_replay': 32}


def create_agent_model() -> Model:
    """Here, a model is created that is taken by DQNAgent and trained during the game.
    You can change anything you like in this function."""

    model = Sequential()

    model.add(Dense(16, activation='relu', input_shape=(get_agent_observation_space(),)))
    model.add(Dense(2))  # flap or no flap

    model.compile(loss='mse', optimizer=Adam(lr=get_agent_parameters()['learning_rate']))
    print(model.summary())
    return model


def callback_on_step(data: dict, episode_ended: bool, episodenumber: int, stepnumber: int) -> None:
    """This method is invoked, when an episode ends, i.e. when the bird reaches 100 pipes or when it crashes.
    Here you can implement functionality to save the weights of the model to a file or perform some kind of logging."""
    global CURRENT_HIGHSCORE

    if episode_ended:
        print(strftime("%Y-%m-%d %H-%M-%S"), end='')
        print("    Ended episode", episodenumber, "after a distance of", data['travelled_distance'], "with reward=",
              data["reward"], ", score=", data['score'], ", epsilon=", data['agent'].epsilon)

        # TODO   Feel free to print more stats here
        # TODO   It is recommended to set a breakingpoint in your debugger on the next line (the `pass`) and then just
        # TODO   crawl through the `data`-dict.
        pass

        # Print notification if a new highscore was met and save the model with current score
        if data['score'] > CURRENT_HIGHSCORE:
            print("++ We got a new highscore:", data['score'])
            data['agent'].model.save('model_'
                                     + INIT_TIME
                                     + '_score_'
                                     + str(data['score'])
                                     + "_" + MATRICULATIONNUMBER + '.h5')
            CURRENT_HIGHSCORE = data['score']

    if data['score'] >= 100:
        print("++ We solved the problem!")
        data['agent'].model.save('model_'
                                 + INIT_TIME
                                 + '_score_'
                                 + str(data['score'])
                                 + "_" + MATRICULATIONNUMBER + '.h5')
        sys.exit(0)


def draw_game_callback(SCREEN, data) -> None:
    """In this function, the game is drawn.

    This function needs to be called by `callback_on_step()` on each step, as here the very crucial line
    `pygame.display.update()` is executed. If this line is not executed each step, you will have a black screen
    and the game will not work. --> Do not touch the return in this function and do not return early from this function.e
                                                                                                                :-)
    """
    import pygame

    # If you want to draw something to the screen, do it here.
    # There is an example added for you.
    #
    draw_random_stuff = True
    if draw_random_stuff:
        # Define some colors
        GREEN = (0, 255, 0)
        RED = (255, 0, 0)
        BLUE = (0, 0, 255)

        pipe = get_next_pipe(data)
        x_distance = get_x_distance(pipe, data)

        # Draw some random stuff
        pygame.draw.line(SCREEN, GREEN,
                         [data["player_x"], data["player_y"]],
                         [data["player_x"] + x_distance, data["player_y"]],
                         2)

        target_upper = get_n_targets(data, n=2)
        for t in target_upper:
            pygame.draw.line(SCREEN, RED,
                             [data["player_x"] + data['player_w'], data["player_y"]],
                             [t["x"], t['y'] + data['pipe_h']],
                             1)

        target_lower = get_n_targets(data, n=2, target='lowerPipes')
        for t in target_lower:
            pygame.draw.line(SCREEN, BLUE,
                             [data["player_x"] + data['player_w'], data["player_y"] + data['player_h']],
                             [t["x"], t['y']],
                             1)

    # Do not change the return of the function and do not return early from this function!
    return pygame.display.update()


def get_next_pipe(data: dict, target='upperPipes'):
    # There are always two pipes available. Determine which pipe is the next one.
    x = data[target][0]["x"] - data['player_center_x'] + data['pipe_w']
    # If the first pipe disappears from screen, we chose to draw to the next pipe.
    return data[target][1] if x < 0 else data[target][0]


def get_next_target(data: dict, target='upperPipes'):
    distance_to_next = data[target][0]["x"] - data['player_w']
    if distance_to_next < 0:
        # inside a pipe -> return pipe exit
        target = data[target][0].copy()
        target['x'] += data['pipe_w']
        return target
    else:
        return data[target][0]


def get_n_targets(data: dict, n: int = 1, target='upperPipes'):
    gaps = []
    # add all entry and exit x of the pipes
    for pipe in data[target]:
        gaps.append(dict(x=pipe['x'], y=pipe['y']))
        gaps.append(dict(x=gaps[-1]['x'] + data['pipe_w'], y=pipe['y']))

    # remove all gaps that are already behind the bird
    gaps = [gap for gap in gaps if gap['x'] - data['player_w'] > 0]

    return gaps[:n]


def get_x_distance(pipe, data: dict):
    return pipe["x"] - data['player_center_x'] + data['pipe_w']


def set_game_enable_training() -> list:
    """"Do not change this function.
    If you start the game with a given argument, the game then loads the model at that filename.
    Otherwise, if no argument is provided, the game does a training run."""

    if len(sys.argv) == 1:
        return [True]
    else:
        return [False, sys.argv[1]]


# Do not modify these variables:
# They are here to save some data from your execution.

# Contains the timestamp of when this program was started (do not change)
INIT_TIME = strftime("%Y-%m-%d-%H-%M-%S")

# Remembers the current highscore.
CURRENT_HIGHSCORE = 0


if __name__ == '__main__':
    """Do not change this function, as it only starts the game."""
    from lib.flappy import FlappyGame
    from lib.ai import FlappyAIManager

    # Create game and initialize it:
    GAME = FlappyGame()
    GAME.init_game()

    # Create an the AI:
    if len(sys.argv) == 2:
        # first arg is the model name
        # It will load a model from that argument filename if an arg is provided
        print("Loading a pre-trained model: " + sys.argv[1])
        AI_manager = FlappyAIManager()
        AI_manager.run_trained_ai(GAME, sys.argv[1])
    elif len(sys.argv) == 1:
        print("Training a new model at", INIT_TIME)
        AI_manager = FlappyAIManager()
        AI_manager.train_ai(GAME)
    else:
        print("Unknown number of arguments:", str(len(sys.argv)))
        sys.exit(-1)


