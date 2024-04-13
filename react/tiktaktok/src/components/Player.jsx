import React, {useState} from 'react';

const MyComponent = ({playerName, playerSymbol, isActice}) => {
    const [initPlayerName, setPlayerName] = useState(playerName);
    const [isEditing, setIsEditing] = useState(false);

    function handleEditClick() {
        // 이 방식은 리엑트에서 추천하는 방식이 아니다.
        /**
        let editBoolean = isEditing ? false : true;
        setIsEditing(editBoolean);
         */
        setIsEditing((booleanEdit) => {
            return !booleanEdit;
        });
    }

    function handleEditName(event){
        setPlayerName(event.target.value);
    }

    let player = <span className='player-name'>{initPlayerName}</span>;
    player = !isEditing ? player : <input type='text' required={true} defaultValue={initPlayerName} onChange={handleEditName}/>

    return (
        <>
            <li className={isActice ? 'active' : undefined}>
                <span className='player'>
                    {player}
                    <span className='player-symbol'>{playerSymbol}</span>
                </span>
                <button onClick={handleEditClick}>{!isEditing ? 'Edit' : 'Save'}</button>
            </li>
        </>
    );
};

export default MyComponent;