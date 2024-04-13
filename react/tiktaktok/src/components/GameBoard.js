import React, {useState} from 'react';

const initalGameBoard = [
    [null, null, null],
    [null, null, null],
    [null, null, null],
];
const MyComponent = ({onSelectSquare, acticePlayerSymbol}) => {
    const [gameBoard, setGameBoard] = useState(initalGameBoard);

    function handleSelectSquare(rowIndex, colIndex){
        /**
         * 게임판을 업데이트 하는데 이전의 상태를 유지하는거를 원한다. 즉 배열에서 하나만 변경되기를 원한다.
         * 만약에 배열이나 객체의 데이터를 변경을 해야될 때 기존의 데이터를 복사하여 참조값을 기반으로 update를 해야된다.
         */
        setGameBoard((prevState)=> {
            let updatedBoard = [...prevState.map(innerArray =>[...innerArray])];
            updatedBoard[rowIndex][colIndex] = acticePlayerSymbol;
            return updatedBoard;
        } );

        onSelectSquare();
    }

    return (
        <>
        <ol id='game-board'>
            {gameBoard.map((row, rowIndex) => (
                <li key={rowIndex}>
                    <ol>
                        {row.map((playerSymbol, colIndex) => (
                            <li key={colIndex}>
                                <button onClick={() => handleSelectSquare(rowIndex, colIndex)}>{playerSymbol}</button>
                            </li>
                        ))}
                    </ol>
                </li>
            ))}
        </ol>
        </>
    );
};

export default MyComponent;
